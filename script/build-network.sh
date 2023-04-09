#!/bin/bash
# Change directory to test network resource
cd fabric-samples/test-network

# Create and join a channel after the network is created
./network.sh up createChannel -ca -c mychannel

# Deploy a chaincode to a channel
./network.sh deployCC -ccn basic -ccp ../asset-transfer-basic/chaincode-javascript/ -ccl javascript