package com.google.android.gms.measurement.internal;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

final class zzfm extends SSLSocket {
    private final SSLSocket zzaun;

    zzfm(zzfl arg1, SSLSocket arg2) {
        super();
        this.zzaun = arg2;
    }

    public final void addHandshakeCompletedListener(HandshakeCompletedListener arg2) {
        this.zzaun.addHandshakeCompletedListener(arg2);
    }

    public final void bind(SocketAddress arg2) {
        this.zzaun.bind(arg2);
    }

    public final void close() {
        __monitor_enter(this);
        try {
            this.zzaun.close();
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
    }

    public final void connect(SocketAddress arg2) {
        this.zzaun.connect(arg2);
    }

    public final void connect(SocketAddress arg2, int arg3) {
        this.zzaun.connect(arg2, arg3);
    }

    public final boolean equals(Object arg2) {
        return this.zzaun.equals(arg2);
    }

    public final SocketChannel getChannel() {
        return this.zzaun.getChannel();
    }

    public final boolean getEnableSessionCreation() {
        return this.zzaun.getEnableSessionCreation();
    }

    public final String[] getEnabledCipherSuites() {
        return this.zzaun.getEnabledCipherSuites();
    }

    public final String[] getEnabledProtocols() {
        return this.zzaun.getEnabledProtocols();
    }

    public final InetAddress getInetAddress() {
        return this.zzaun.getInetAddress();
    }

    public final InputStream getInputStream() {
        return this.zzaun.getInputStream();
    }

    public final boolean getKeepAlive() {
        return this.zzaun.getKeepAlive();
    }

    public final InetAddress getLocalAddress() {
        return this.zzaun.getLocalAddress();
    }

    public final int getLocalPort() {
        return this.zzaun.getLocalPort();
    }

    public final SocketAddress getLocalSocketAddress() {
        return this.zzaun.getLocalSocketAddress();
    }

    public final boolean getNeedClientAuth() {
        return this.zzaun.getNeedClientAuth();
    }

    public final boolean getOOBInline() {
        return this.zzaun.getOOBInline();
    }

    public final OutputStream getOutputStream() {
        return this.zzaun.getOutputStream();
    }

    public final int getPort() {
        return this.zzaun.getPort();
    }

    public final int getReceiveBufferSize() {
        int v0_1;
        __monitor_enter(this);
        try {
            v0_1 = this.zzaun.getReceiveBufferSize();
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1;
    }

    public final SocketAddress getRemoteSocketAddress() {
        return this.zzaun.getRemoteSocketAddress();
    }

    public final boolean getReuseAddress() {
        return this.zzaun.getReuseAddress();
    }

    public final int getSendBufferSize() {
        int v0_1;
        __monitor_enter(this);
        try {
            v0_1 = this.zzaun.getSendBufferSize();
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1;
    }

    public final SSLSession getSession() {
        return this.zzaun.getSession();
    }

    public final int getSoLinger() {
        return this.zzaun.getSoLinger();
    }

    public final int getSoTimeout() {
        int v0_1;
        __monitor_enter(this);
        try {
            v0_1 = this.zzaun.getSoTimeout();
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1;
    }

    public final String[] getSupportedCipherSuites() {
        return this.zzaun.getSupportedCipherSuites();
    }

    public final String[] getSupportedProtocols() {
        return this.zzaun.getSupportedProtocols();
    }

    public final boolean getTcpNoDelay() {
        return this.zzaun.getTcpNoDelay();
    }

    public final int getTrafficClass() {
        return this.zzaun.getTrafficClass();
    }

    public final boolean getUseClientMode() {
        return this.zzaun.getUseClientMode();
    }

    public final boolean getWantClientAuth() {
        return this.zzaun.getWantClientAuth();
    }

    public final boolean isBound() {
        return this.zzaun.isBound();
    }

    public final boolean isClosed() {
        return this.zzaun.isClosed();
    }

    public final boolean isConnected() {
        return this.zzaun.isConnected();
    }

    public final boolean isInputShutdown() {
        return this.zzaun.isInputShutdown();
    }

    public final boolean isOutputShutdown() {
        return this.zzaun.isOutputShutdown();
    }

    public final void removeHandshakeCompletedListener(HandshakeCompletedListener arg2) {
        this.zzaun.removeHandshakeCompletedListener(arg2);
    }

    public final void sendUrgentData(int arg2) {
        this.zzaun.sendUrgentData(arg2);
    }

    public final void setEnableSessionCreation(boolean arg2) {
        this.zzaun.setEnableSessionCreation(arg2);
    }

    public final void setEnabledCipherSuites(String[] arg2) {
        this.zzaun.setEnabledCipherSuites(arg2);
    }

    public final void setEnabledProtocols(String[] arg3) {
        Object[] v3_1;
        if(arg3 != null && (Arrays.asList(((Object[])arg3)).contains("SSLv3"))) {
            ArrayList v3 = new ArrayList(Arrays.asList(this.zzaun.getEnabledProtocols()));
            if(((List)v3).size() > 1) {
                ((List)v3).remove("SSLv3");
            }

            v3_1 = ((List)v3).toArray(new String[((List)v3).size()]);
        }

        this.zzaun.setEnabledProtocols(((String[])v3_1));
    }

    public final void setKeepAlive(boolean arg2) {
        this.zzaun.setKeepAlive(arg2);
    }

    public final void setNeedClientAuth(boolean arg2) {
        this.zzaun.setNeedClientAuth(arg2);
    }

    public final void setOOBInline(boolean arg2) {
        this.zzaun.setOOBInline(arg2);
    }

    public final void setPerformancePreferences(int arg2, int arg3, int arg4) {
        this.zzaun.setPerformancePreferences(arg2, arg3, arg4);
    }

    public final void setReceiveBufferSize(int arg2) {
        __monitor_enter(this);
        try {
            this.zzaun.setReceiveBufferSize(arg2);
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
    }

    public final void setReuseAddress(boolean arg2) {
        this.zzaun.setReuseAddress(arg2);
    }

    public final void setSendBufferSize(int arg2) {
        __monitor_enter(this);
        try {
            this.zzaun.setSendBufferSize(arg2);
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
    }

    public final void setSoLinger(boolean arg2, int arg3) {
        this.zzaun.setSoLinger(arg2, arg3);
    }

    public final void setSoTimeout(int arg2) {
        __monitor_enter(this);
        try {
            this.zzaun.setSoTimeout(arg2);
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
    }

    public final void setTcpNoDelay(boolean arg2) {
        this.zzaun.setTcpNoDelay(arg2);
    }

    public final void setTrafficClass(int arg2) {
        this.zzaun.setTrafficClass(arg2);
    }

    public final void setUseClientMode(boolean arg2) {
        this.zzaun.setUseClientMode(arg2);
    }

    public final void setWantClientAuth(boolean arg2) {
        this.zzaun.setWantClientAuth(arg2);
    }

    public final void shutdownInput() {
        this.zzaun.shutdownInput();
    }

    public final void shutdownOutput() {
        this.zzaun.shutdownOutput();
    }

    public final void startHandshake() {
        this.zzaun.startHandshake();
    }

    public final String toString() {
        return this.zzaun.toString();
    }
}

