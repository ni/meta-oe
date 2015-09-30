DESCRIPTION = "library for easy implementation of a RDP/VNC server"
HOMEPAGE = "https://libvnc.github.io"
SECTION = "libs"
PRIORITY = "optional"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=361b6b837cad26c6900a926b62aada5f"

DEPENDS += "zlib jpeg libpng gtk+ libgcrypt nettle gnutls gmp"
RDEPENDS_${PN} += "libpng gtk+ libgcrypt"

inherit autotools pkgconfig

SRC_URI  = "https://github.com/LibVNC/libvncserver/archive/LibVNCServer-0.9.10.tar.gz"

SRC_URI[md5sum] = "e1b888fae717b06896f8aec100163d27"
SRC_URI[sha256sum] = "ed10819a5bfbf269969f97f075939cc38273cc1b6d28bccfb0999fba489411f7"

S = "${WORKDIR}/${PN}-LibVNCServer-${PV}"

EXTRA_OEMAKE_append=" SUBDIRS='libvncserver' "
