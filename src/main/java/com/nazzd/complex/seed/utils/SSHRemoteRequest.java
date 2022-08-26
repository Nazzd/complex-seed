package com.nazzd.complex.seed.utils;


import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

@Slf4j
public class SSHRemoteRequest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SSHRemoteRequest.class);
    private static SSHRemoteRequest sshRemoteRequest;

    public SSHRemoteRequest() {
    }

    private final static int DEFAULT_PORT = 22;
    private int port;
    private String ip;
    private String user;
    private String password;

    private Session session;
    private boolean logined = false;

    public SSHRemoteRequest(String ip, String user, String password) {
        this(ip, DEFAULT_PORT, user, password);
    }

    public SSHRemoteRequest(String ip, int port, String user, String password) {
        super();
        this.ip = ip;
        this.port = port;
        this.user = user;
        this.password = password;
    }


    /**
     * 登陆远程服务器
     *
     * @param ip
     * @param user
     * @param password
     * @param port
     * @throws Exception
     */
    public void sshRemoteLogin(String ip, String user, String password, int port) throws Exception {
        if (logined) {
            return;
        }
        JSch jSch = new JSch();
        try {
            session = jSch.getSession(user, ip, port);
            session.setPassword(password);
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.setTimeout(3000);
            session.connect();
            logined = true;
        } catch (JSchException e) {
            logined = false;
            throw new Exception("远程主机登录失败, ip为：" + ip + ", user为：" + user + ", 错误原因为：" + e.getMessage());
        }

    }


    /**
     * 关闭连接
     */
    public void closeSession() {
        // 调用session的关闭连接的方法
        if (session != null) {
            // 如果session不为空,调用session的关闭连接的方法
            session.disconnect();
            logined = false;
        }
    }


    /**
     * 远程执行命令，无返回值
     *
     * @param command 多条命令用&&连接
     * @throws IOException
     */
    public void execCommand(String command) throws IOException {
        InputStream in = null;
        Channel channel = null;
        try {
            // 如果命令command不等于null
            if (command != null) {
                // 打开channel
                channel = session.openChannel("exec");
                // 设置command
                ((ChannelExec) channel).setCommand(command);
                // channel进行连接
                channel.connect();
                // 获取到输入流
                in = channel.getInputStream();
                // 执行相关的命令
                String processDataStream = processDataStream(in);
                // 打印相关的命令
                log.info("执行结果为：" + processDataStream);
            }
        } catch (Exception e) {
            LOGGER.error("error:" + e.getMessage());
            throw new RuntimeException("执行命令失败, ip为：" + ip + ", 错误原因为：" + e.getMessage());
        } finally {
            if (in != null) {
                in.close();
            }
            if (channel != null) {
                channel.disconnect();
            }
        }

    }


    public String processDataStream(InputStream in) throws Exception {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String result = "";
            while ((result = br.readLine()) != null) {
                sb.append(result);
            }
        } catch (Exception e) {
            throw new Exception("获取数据流失败: " + e);
        }
        return sb.toString();
    }


    /**
     * 远程执行命令，有返回值
     *
     * @param command
     * @return
     * @throws IOException
     */
    public String executeCommand(String command) throws IOException {
        // 输入流(读)
        InputStream in = null;
        // 定义channel变量
        Channel channel = null;
        try {
            // 如果命令command不等于null
            if (command != null) {
                // 打开channel
                //说明：exec用于执行命令;sftp用于文件处理
                channel = session.openChannel("exec");
                // 设置command
                ((ChannelExec) channel).setCommand(command);
                // channel进行连接
                channel.connect();
                // 获取到输入流
                in = channel.getInputStream();
                // 执行相关的命令
                String processDataStream = processDataStream(in);
                // 打印相关的命令
                log.info("执行结果为：" + processDataStream);
                return processDataStream;
            }
        } catch (Exception e) {
            LOGGER.error("error:" + e.getMessage());
            throw new RuntimeException("执行命令失败, ip为：" + ip + ", 错误原因为：" + e.getMessage());
        } finally {
            if (in != null) {
                in.close();
            }
            if (channel != null) {
                channel.disconnect();
            }
        }
        return "";
    }


    /**
     * @param directory  上传文件的目录
     * @param uploadFile 将要上传的文件
     */
    public void uploadFile(String directory, String uploadFile) {
        try {
            // 打开channelSftp
            ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
            // 远程连接
            channelSftp.connect();
            // 创建一个文件名称问uploadFile的文件
            File file = new File(uploadFile);
            // 将文件进行上传(sftp协议)
            // 采用默认的传输模式:OVERWRITE
            channelSftp.put(new FileInputStream(file), directory, ChannelSftp.OVERWRITE);
            // 切断远程连接
            channelSftp.exit();
            log.info("文件：" + file.getName() + "上传成功");
        } catch (JSchException | SftpException | FileNotFoundException e) {
            LOGGER.error("error:" + e.getMessage());
        }

    }

    public static void main(String[] args) {
        SSHRemoteRequest sshRemoteRequest = new SSHRemoteRequest("", 1, "", "");

    }


}
