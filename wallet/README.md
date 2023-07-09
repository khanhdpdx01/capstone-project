# Start blockchain explorer

```sh
git clone https://github.com/hyperledger/blockchain-explorer.git
```

```sh
docker run -d --name explorerdb \
-p 5432:5432 \
-e DATABASE_DATABASE=fabricexplorer \
-e DATABASE_USERNAME=hppoc \
-e DATABASE_PASSWORD=password \
-v pgdata:/var/lib/postgresql/data hyperledger/explorer-db:latest
```

```sh
cd blockchain-explorer
npm stall
cd client/
npm install
npm run build
```

```json
{
        "name": "test-network",
        "version": "1.0.0",
        "license": "Apache-2.0",
        "client": {
                "tlsEnable": true,
                "adminCredential": {
                        "id": "exploreradmin",
                        "password": "exploreradminpw"
                },
                "enableAuthentication": false,
                "organization": "Org1MSP",
                "connection": {
                        "timeout": {
                                "peer": {
                                        "endorser": "300"
                                },
                                "orderer": "300"
                        }
                }
        },
        "channels": {
                "mychannel": {
                        "peers": {
                                "peer0.manufacturer.com": {}
                        },
                        "connection": {
                                "timeout": {
                                        "peer": {
                                                "endorser": "6000",
                                                "eventHub": "6000",
                                                "eventReg": "6000"
                                        }
                                }
                        }
                }
        },
        "organizations": {
                "Org1MSP": {
                        "mspid": "Org1MSP",
                        "adminPrivateKey": {
                                "path": "/home/khanhdpdx/traceabilty-rice-network/organizations/peerOrganizations/manufacturer.com/users/Admin@manufacturer.com/msp/keystore/888c78bc6ec4e237b63a68663c7bf5881e09aa86a61296801b4f8013cae68112_sk"
                        },
                        "peers": ["peer0.manufacturer.com"],
                        "signedCert": {
                                "path": "/home/khanhdpdx/traceabilty-rice-network/organizations/peerOrganizations/manufacturer.com/users/Admin@manufacturer.com/msp/signcerts/cert.pem"
                        }
                }
        },
        "peers": {
                "peer0.manufacturer.com": {
                        "url": "grpcs://localhost:7051",
                        "tlsCACerts": {
                                "path": "/home/khanhdpdx/traceabilty-rice-network/organizations/peerOrganizations/manufacturer.com/peers/peer0.manufacturer.com/tls/ca.crt"
                        },
                        "grpcOptions": {
                                "ssl-target-name-override": "peer0.manufacturer.com"
                        }
                }
        }
}
```