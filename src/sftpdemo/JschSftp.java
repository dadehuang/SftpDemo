/*
 * Copyright 2010-2012 sshj contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sftpdemo;

import com.jcraft.jsch.*;
import java.io.*;

import java.io.IOException;

/** This example demonstrates downloading of a file over SCP from the SSH server. */
public class JschSftp {

    public static void main(String[] args) {
       //jsch_sftp();
       jsch_download();
    }
  
    public static void jsch_sftp()
    {
        String user = "huangd";
        String password = "dade123";
        String host = "129.199.0.44";
        int port=22;

        String remoteFile="sample.txt";

        try
        {
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            System.out.println("Establishing Connection...");
            session.connect();
            System.out.println("Connection established.");
            System.out.println("Creating SFTP Channel.");
            ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
            sftpChannel.connect();
            System.out.println("SFTP Channel created.");
            InputStream out= null;
            out= sftpChannel.get(remoteFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(out));
            String line;
            while ((line = br.readLine()) != null) 
            {
                System.out.println(line);
            }
            br.close();
            sftpChannel.disconnect();
            session.disconnect();
        }
        catch(JSchException | SftpException | IOException e)
        {
            System.out.println(e);
        }
    }
    
    public static void jsch_download()
    {
        String user = "huangd";
        String password = "dade123";
        String host = "129.199.0.44";
        int port=22;

        String remoteFile="sample.txt";
        String localFile="C:/Temp/sample.txt";
        
        String remoteFile2="sample2.txt";
        String localFile2="C:/Temp/sample2.txt";

        try
        {
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            System.out.println("Establishing Connection...");
            session.connect();

            System.out.println("Connection established.");
            System.out.println("Creating SFTP Channel.");
            ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
            sftpChannel.connect();
            System.out.println("SFTP Channel created.");
            
            System.out.println("Download file from remote.");
            sftpChannel.get(remoteFile, localFile);
            
            System.out.println("Upload file to remote.");
            sftpChannel.put(localFile2, remoteFile2 );
            sftpChannel.disconnect();
            session.disconnect();
        }
        catch(JSchException | SftpException e)
        {
            System.out.println(e);
        }
    }
    
  }
