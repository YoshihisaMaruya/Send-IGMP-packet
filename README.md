
IGMPパケットを送信するアプリケーション.

現在、Membership Reportのみ対応.

---
# v2,v3に対応について
v2,v3のパケットを送信できるかどうかはカーネル依存しています.

## Mac OS Xの場合
IGMPv3を送信することが出来ますが、sourceを指定することが出来ません.

また、

    sysctl net.inet.igmp.v1enable=0
    sysctl net.inet.igmp.v2enable=0
    
とすることで強制的にIGMP v3に固定することが出来ます.


## Debianの場合
IGMPv3を送信することが出来、sourceも指定することが出来ます.

しかしながら、一度	v2のQueryを受け取るとカーネルレベルで強制的にv2になります
(force_igmpで指定しても無意味です).

### niごとのigmpバージョンを確認する方法
    cat /proc/net/igmp

###  v2に固定する方法
    echo "2" > /proc/sys/net/ipv4/conf/eth0/force_igmp_version


# マルチキャストのdestinationアドレスについて
*  IGMPv2 : 224.0.0.1
*  IGMPv3 : 224.0.0.22