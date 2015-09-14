from scapy.all import *
import scapy.contrib.igmp



a=Ether()
b=IP()
c=IGMP(type=0x11, gaddr="224.2.3.4")
c.igmpize(b, a)
print("Joining IP " + c.gaddr + " MAC " + a.dst)
sendp(a/b/c, iface="en0")

