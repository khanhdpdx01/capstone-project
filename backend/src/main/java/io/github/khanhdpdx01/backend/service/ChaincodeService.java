package io.github.khanhdpdx01.backend.service;

import org.hyperledger.fabric.gateway.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ChaincodeService {
    private final String walletLocation = "/home/khanh/capstone/wallet";
    private final String connectionProfile = "/home/khanh/capstone/wallet/connection.json";
    @Value("${fabric.channel-name}")
    private String channel;
    @Value("${fabric.chaincode-name}")
    private String chaincode;

    private Gateway connect() throws Exception {
        // Load a file system based wallet for managing identities.
        Path walletPath = Paths.get(walletLocation);
        Wallet wallet = Wallets.newFileSystemWallet(walletPath);
        // load a CCP
        Path networkConfigPath = Paths.get(connectionProfile);
        Gateway.Builder builder = Gateway.createBuilder();
        builder.identity(wallet, "admin").networkConfig(networkConfigPath).discovery(true);
        return builder.connect();
    }

    public Transaction createTransaction(String name) throws Exception {
        Gateway gateway = connect();
        Network network = gateway.getNetwork(channel);
        Contract contract = network.getContract(chaincode);
        return contract.createTransaction(name);
    }
}
