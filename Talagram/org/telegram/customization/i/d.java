package org.telegram.customization.i;

import f.b.a;
import f.b.f;
import f.b.o;
import f.b.t;
import f.b;
import java.util.ArrayList;
import okhttp3.ab;
import org.telegram.customization.Model.ProxyServerModel;

public interface d {
    @f(a="filters") b a();

    @f(a="call") b a(@t(a="uid") int arg1, @t(a="callee") int arg2);

    @f(a="config") b a(@t(a="appId") int arg1, @t(a="appVer") int arg2, @t(a="apiVer") int arg3);

    @f(a="launch") b a(@t(a="uid") int arg1, @t(a="token") String arg2, @t(a="fcm") String arg3);

    @f(a="call/rate") b a(@t(a="ip") String arg1, @t(a="callid") String arg2, @t(a="sipid") int arg3, @t(a="token") String arg4, @t(a="rate") int arg5, @t(a="msg") String arg6);

    @f(a="call/start") b a(@t(a="ip") String arg1, @t(a="callid") String arg2, @t(a="uid") long arg3);

    @o(a="info") b a(@t(a="uname") String arg1, @t(a="fullname") String arg2, @t(a="type") String arg3, @t(a="appId") int arg4, @t(a="appVer") int arg5, @t(a="apiVer") int arg6, @a ab arg7);

    @o(a="info") b a(@t(a="uname") String arg1, @t(a="fullname") String arg2, @t(a="type") String arg3, @t(a="appId") int arg4, @t(a="appVer") int arg5, @t(a="apiVer") int arg6, @a ab arg7, @t(a="deviceId") String arg8);

    @o(a="info") b a(@t(a="uname") String arg1, @t(a="fullname") String arg2, @t(a="type") String arg3, @t(a="appId") int arg4, @t(a="appVer") int arg5, @t(a="apiVer") int arg6, @a ab arg7, @t(a="token") String arg8, @t(a="fcm") String arg9, @t(a="lhid") String arg10);

    @o(a="cif") b a(@a ArrayList arg1);

    @o(a="cstats") b a(@a ab arg1);

    @o(a="proxy") b a(@a ProxyServerModel arg1, @t(a="dtk") String arg2);

    @o(a="churl") b a(@t(a="gprs") boolean arg1, @t(a="car") String arg2, @a ab arg3);

    @f(a="themes") b b();

    @o(a="info") b b(@t(a="uname") String arg1, @t(a="fullname") String arg2, @t(a="type") String arg3, @t(a="appId") int arg4, @t(a="appVer") int arg5, @t(a="apiVer") int arg6, @a ab arg7);

    @o(a="safe") b b(@a ab arg1);

    @f(a="urljob") b c();
}

