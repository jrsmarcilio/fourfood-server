# OXE FOOD - MARCILIO

# Install and start the docker engine
sudo yum update -y
sudo amazon-linux-extras install docker
sudo service docker start
sudo service docker status
sudo usermod -a -G docker ec2-user
sudo docker info
sudo docker login
 - Username:
 - Password: 
sudo docker info
sudo su -u ec2-user


# Setup the docker-compose
sudo curl -L https://github.com/docker/compose/releases/latest/download/docker-compose-$(uname -s)-$(uname -m) -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose
mv /usr/local/bin/docker-compose /bin/docker-compose