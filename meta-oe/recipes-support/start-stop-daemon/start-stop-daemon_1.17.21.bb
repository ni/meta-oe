SUMMARY = "Debian's start-stop-daemon utility extracted from the dpkg \
package"
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://utils/start-stop-daemon.c;endline=21;md5=8fbd0497a7d0b01e99820bffcb58e9ad"
# start-stop-daemon is usually shipped by dpkg
DEPENDS = "ncurses"
RCONFLICTS_${PN} = "dpkg"

SRC_URI = " \
    ${DEBIAN_MIRROR}/main/d/dpkg/dpkg_${PV}.tar.xz \
    file://0001-dpkg-start-stop-daemon-Accept-SIG-prefixed-signal-na.patch \
"

SRC_URI[md5sum] = "765a96fd0180196613bbfa3c4aef0775"
SRC_URI[sha256sum] = "3ed776627181cb9c1c9ba33f94a6319084be2e9ec9c23dd61ce784c4f602cf05"

inherit autotools gettext pkgconfig

S = "${WORKDIR}/dpkg-${PV}"

EXTRA_OECONF = " \
    --with-start-stop-daemon \
    --without-bz2 \
    --without-install-info \
    --without-selinux \
    --without-update-alternatives \
"

do_install_append () {
    # remove everything that is not related to start-stop-daemon, since there
    # is no explicit rule for only installing ssd
    find ${D} -type f -not -name "*start-stop-daemon*" -exec rm {} \;
    find ${D} -depth -type d -empty -exec rmdir {} \;

    # support for buggy init.d scripts that refer to an alternative
    # explicit path to start-stop-daemon
    mkdir -p ${D}/sbin/
    ln -sf /usr/sbin/start-stop-daemon ${D}/sbin/start-stop-daemon
}
