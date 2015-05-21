SUMMARY = "The calibrator and other useful utilities for TI wireless solution based on wl12xx driver"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=4725015cb0be7be389cf06deeae3683d"

DEPENDS = "libnl"

PR ="r9"
PV = "0.0+gitr${SRCPV}"

SRCREV = "06dbdb2727354b5f3ad7c723897f40051fddee49"
SRC_URI = "git://github.com/gxk/ti-utils.git;protocol=git \
           file://0001-calibrator-support-compilation-with-a-custom-include.patch \
"

S = "${WORKDIR}/git"

export CROSS_COMPILE = "${TARGET_PREFIX}"
export NLVER = "3"
CFLAGS += " -DCONFIG_LIBNL20 -I${STAGING_INCDIR}/libnl3"

do_install() {
	install -d ${D}${bindir}
	install -m 0755 calibrator ${D}${bindir}/
}
