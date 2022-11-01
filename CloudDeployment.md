
# Deployment to Docker and Kubernetes with AWS  
<hr>

## Step 1: Creating a EC2 instance 
1. Goto EC2 > Instances > Launch an instance
2. Name: ```jee-group3-sprint2```
3. Select Amazon Machine Image as ```Amazon Linux 2 (64-bit)```
4. Select Instance type as ```t2.micro``` (free tier)
5. select Keypair ```jee-group3-key```
6. Select Network as ``` raj2244-vpc```
7. Ensure a **public subnet** is selected
8. Select Security group as ```jee-group3-securitygroup``` (Open port 22 if creating new security group).
9. Review Config and click on **Launch Instance**

## Step 2: Connect to EC2 Instance
1. Change the access rights of the key to single user using ```chmod 400 <key_name>```.
2. Open terminal at the location of key.
3. Connect via SSH using ```ssh -i <key_name> ec2-user@<public_dns>```.
4. When connecting for the first time or with rebooted instance systems asks to save the fingerprint enter ```yes``` on the terminal prompt.

## Step 3: Copy jar file to instance
1. Use following command:  
```scp -i "jee-group3-key.pem" .\flights-0.0.1-SNAPSHOT.jar ec2-user@ec2-3-219-170-106.compute-1.amazonaws.com:```.
2. Rename the file after connecting to EC2 ```mv flights-0.0.1-SNAPSHOT.jar flights.jar```
3. 

## Step 4: Configure Docker on EC2
1. To install Docker  ```sudo yum install docker -y```.
2. To start docker service ```sudo service docker start```
3. To enable root access to ec2-user ```sudo usermod -a -G docker ec2-user```. (logout and reconnect to revaluate authorization of ec2-user)
4. To check docker information ```docker info```

## Step 5: Build Docker Image
1. Create a Dockerfile ```vim Dockerfile```.
2. Paste following content in the Dockerfile
```
From eclipse-temurin:11
RUN mkdir -p /www/app/
COPY flights.jar /www/app/
CMD ["java","-jar","/www/app/flights.jar"] 
```
3. To build Docker Image ```docker build -t flightapp .```.
Here -t takes a tag (tag of latest is added if not mentioned)
4. View images by ```docker images```.
5. Run Image using: ```docker run -it --rm flightapp:latest```
-i : interactive container
-t : tty
--rm : remove container after usage.

## Step 6: Pushing to DockerHub
1. Create a DockerHub Account
2. Create a public repository on dockerHub by the name: ```flightapp```
3. Login inside instance: ```docker login```.
4. Enter Username and password of DockerHub.
5. Rename the image for pushing by: ```docker tag flightapp <dockerHub_username>/flightapp:latest```.
6. Push to repository by: ```docker push <dockerHub_username>/flightapp:latest```.

## Step 7: Installing Docker Compose
1. Download docker compose by:  
```sudo curl -L https://github.com/docker/compose/releases/latest/download/docker-compose-$(uname -s)-$(uname -m) -o /usr/local/bin/docker-compose```
2. ```sudo chmod +x /usr/local/bin/docker-compose```
3. ```docker-compose version```. (version during creation: ```v2.12.2```)
## Step 8:  Creating Docker Image Configuration
1. Pull postgres image: ```docker pull postgres:14-alpine``` (alpine is lightweight and apt for our app).
2. Create a yaml file: ```vim docker-compose.yml```.
3. Paste following inside the yaml file:
```
version: '3.5'
services:
  flights:
    image: skieer/flightapp
    ports:
      - 8080:8080
    environment:
      SPRING_DATASOURCE_URL: 'jdbc:postgresql://postgres:5432/flights'      SPRING_DATASOURCE_USERNAME: postgres
      SPRING_DATASOURCE_PASSWORD: abc12345

  postgres:
    image: postgres:14-alpine
    ports:
      - 5432:5432
    environment:
      - POSTGRES_DB=flights
      - POSTGES_USER=postgres
      - POSTGRES_PASSWORD=abc12345
```
4. Run the config file using: ```docker-compose up -d```  
-d : run in detached state
5. Access application using ```<public_dns>:8080/api/login``` and test on Postman.

## Step 9: Install Kubectl and eksctl
### Kubectl
1. ```curl -o kubectl https://s3.us-west-2.amazonaws.com/amazon-eks/1.22.6/2022-03-09/bin/linux/amd64/kubectl```
2. ```chmod +x ./kubectl```
3. ```mkdir -p $HOME/bin && cp ./kubectl $HOME/bin/kubectl && export PATH=$PATH:$HOME/bin```
4. ```kubectl version --short --client``` (Version during Creation: ```Client Version: v1.22.6-eks-7d68063```)
### eksctl
1. ```curl --silent --location https://github.com/weaveworks/eksctl/releases/latest/download/eksctl_$(uname -s)_amd64.tar.gz | tar xz -C /tmp```
2. ```sudo mv /tmp/eksctl /usr/local/bin```
3. ```eksctl version``` (Version during Creation: ```0.116.0```)

## Step 10: Create IAM Role
#### IAM Permissions for Role

1. AmazonEC2FullAccess

2. IAMFullAccess

3. AdministratorAccess

4. AmazonVPCFullAccess

5. AWSCloudFormationFullAccess

## Step 11: Attach IAM Role
1. Instance Dashboard > Actions > Security > Modify IAM Role
2. Add the created role

## Step 12: Create Kubernetes cluster with eksctl
1. ```eksctl create cluster --name jeeaws-group3 --region us-east-1 --zones --node-type t2.medium --nodes-min 1 --nodes-max 2```
### Deployment Config File
2. Create deployment file ```vim deploy.yml```
3. Paste the [deploy.yml](./deploy.yml) from codebase after modifications:


### Service Config File
4. Create Service config file : ```vim service.yml```.

5. Paste the [service.yml](./service.yml) from codebase after modifications:

## Step 13: Apply the Deployment and Service Config to Kubernetes
1. ```kubectl apply -f deploy.yml```.
2. ```kubectl apply -f service.yml```.
3. To view Pods: ```kubectl get pods -o wide```