package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

public final class UdpDataSource extends BaseDataSource {
    public final class UdpDataSourceException extends IOException {
        public UdpDataSourceException(IOException arg1) {
            super(((Throwable)arg1));
        }
    }

    public static final int DEAFULT_SOCKET_TIMEOUT_MILLIS = 8000;
    public static final int DEFAULT_MAX_PACKET_SIZE = 2000;
    private InetAddress address;
    private MulticastSocket multicastSocket;
    private boolean opened;
    private final DatagramPacket packet;
    private final byte[] packetBuffer;
    private int packetRemaining;
    private DatagramSocket socket;
    private InetSocketAddress socketAddress;
    private final int socketTimeoutMillis;
    private Uri uri;

    public UdpDataSource(TransferListener arg2) {
        this(arg2, 2000);
    }

    public UdpDataSource(TransferListener arg2, int arg3) {
        this(arg2, arg3, 8000);
    }

    public UdpDataSource(TransferListener arg3, int arg4, int arg5) {
        super(true);
        this.socketTimeoutMillis = arg5;
        this.packetBuffer = new byte[arg4];
        this.packet = new DatagramPacket(this.packetBuffer, 0, arg4);
        if(arg3 != null) {
            this.addTransferListener(arg3);
        }
    }

    public void close() {
        Uri v0 = null;
        this.uri = v0;
        if(this.multicastSocket != null) {
            try {
                this.multicastSocket.leaveGroup(this.address);
                goto label_7;
            }
            catch(IOException ) {
            label_7:
                this.multicastSocket = ((MulticastSocket)v0);
            }
        }

        if(this.socket != null) {
            this.socket.close();
            this.socket = ((DatagramSocket)v0);
        }

        this.address = ((InetAddress)v0);
        this.socketAddress = ((InetSocketAddress)v0);
        this.packetRemaining = 0;
        if(this.opened) {
            this.opened = false;
            this.transferEnded();
        }
    }

    public Uri getUri() {
        return this.uri;
    }

    public long open(DataSpec arg4) {
        DatagramSocket v0_2;
        this.uri = arg4.uri;
        String v0 = this.uri.getHost();
        int v1 = this.uri.getPort();
        this.transferInitializing(arg4);
        try {
            this.address = InetAddress.getByName(v0);
            this.socketAddress = new InetSocketAddress(this.address, v1);
            if(this.address.isMulticastAddress()) {
                this.multicastSocket = new MulticastSocket(this.socketAddress);
                this.multicastSocket.joinGroup(this.address);
                MulticastSocket v0_1 = this.multicastSocket;
            }
            else {
                v0_2 = new DatagramSocket(this.socketAddress);
            }

            this.socket = v0_2;
        }
        catch(IOException v4) {
            throw new UdpDataSourceException(v4);
        }

        try {
            this.socket.setSoTimeout(this.socketTimeoutMillis);
        }
        catch(SocketException v4_1) {
            throw new UdpDataSourceException(((IOException)v4_1));
        }

        this.opened = true;
        this.transferStarted(arg4);
        return -1;
    }

    public int read(byte[] arg3, int arg4, int arg5) {
        if(arg5 == 0) {
            return 0;
        }

        if(this.packetRemaining == 0) {
            try {
                this.socket.receive(this.packet);
            }
            catch(IOException v3) {
                throw new UdpDataSourceException(v3);
            }

            this.packetRemaining = this.packet.getLength();
            this.bytesTransferred(this.packetRemaining);
        }

        int v0 = this.packet.getLength() - this.packetRemaining;
        arg5 = Math.min(this.packetRemaining, arg5);
        System.arraycopy(this.packetBuffer, v0, arg3, arg4, arg5);
        this.packetRemaining -= arg5;
        return arg5;
    }
}

