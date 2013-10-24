SUMMARY = "A full-featured SSL VPN solution via tun device."
HOMEPAGE = "http://openvpn.sourceforge.net"
SECTION = "console/network"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=5aac200199fde47501876cba7263cb0c"
DEPENDS = "lzo openssl iproute2 ${@base_contains('DISTRO_FEATURES', 'pam', 'libpam', '', d)}"

inherit autotools

SRC_URI = "http://swupdate.openvpn.org/community/releases/openvpn-${PV}.tar.gz \
           file://openvpn"

SRC_URI[md5sum] = "06e5f93dbf13f2c19647ca15ffc23ac1"
SRC_URI[sha256sum] = "20bda3f9debb9a52db262aecddfa4e814050a9404a9106136b7e3b6f7ef36ffc"

CFLAGS += "-fno-inline"

# I want openvpn to be able to read password from file (hrw)
EXTRA_OECONF += "--enable-password-save --enable-iproute2"

do_install_append() {
    install -d ${D}/${sysconfdir}/init.d
    install -d ${D}/${sysconfdir}/openvpn
    install -m 755 ${WORKDIR}/openvpn ${D}/${sysconfdir}/init.d
}

RRECOMMENDS_${PN} = "kernel-module-tun"

INSANE_SKIP_${PN} = "debug-files"
