#!/bin/bash
# Install docker if it don't exist
if ! [ -x "$(command -v docker)" ]; then
  curl -fsSL https://get.docker.com -o get-docker.sh | bash get-docker.sh

  # allow permission docker.sock
  sudo chmod 666 /var/run/docker.sock
 
  # install docker-compose 
  sudo apt-get install docker-compose -y
fi

# Download Fabric samples, docker images, and binaries
curl -sSL https://bit.ly/2ysbOFE | bash -s -- 2.4.9 1.5.5

# Install jq if it don't exist
if ! [ -x "$(command -v jq)" ]; then
  sudo apt-get install jq -y
fi

# Install java if it don't exist
if ! [ -x "$(command -v java)" ]; then
  sudo apt-get install openjdk-11-jdk -y

  # Setting the JAVA_HOME Environment Variable
  echo 'JAVA_HOME="/usr/lib/jvm/java-11-openjdk-amd64"' >> /etc/environment

  # Apply the changes
  source /etc/environment
fi

# Install IPFS node if it don't exist
if ! [ -x "$(command -v ipfs)" ]; then
  # Download the Linux binary from dist.ipfs.tech
  wget https://dist.ipfs.tech/kubo/v0.19.0/kubo_v0.19.0_linux-amd64.tar.gz

  # Unzip the file
  tar -xvzf kubo_v0.19.0_linux-amd64.tar.gz

  # Run the install script
  sudo bash kubo/install.sh
fi

if ! [ -x "$(command version go)" ]; then
  curl -OL https://go.dev/dl/go1.20.5.linux-amd64.tar.gz

  rm -rf /usr/local/go && tar -C /usr/local -xzf go1.20.5.linux-amd64.tar.gz

  export PATH=$PATH:/usr/local/go/bin
fi

