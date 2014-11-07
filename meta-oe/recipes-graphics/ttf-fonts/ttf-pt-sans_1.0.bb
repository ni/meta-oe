SUMMARY = "PT Sans Fonts"
DESCRIPTION = "The PT Sans TTF font set"
HOMEPAGE = "http://www.paratype.com/public/"
BUGTRACKER = "n/a"

SECTION = "x11/fonts"

LICENSE = "OFL-1.1"
LIC_FILES_CHKSUM = "file://../PTSansPTSerifOFL.txt;md5=8400c100cc23eb366e978adb4782a666"

inherit allarch

RDEPENDS_${PN} = "fontconfig-utils"
PR = "1"

inherit fontcache

FONT_PACKAGES = "${PN}"

SRC_URI = "http://www.fontstock.com/public/PTSansOFL.zip"

SRC_URI[md5sum] = "93b4e9d4099c7dbf043db92c5c43e40f"
SRC_URI[sha256sum] = "7105b5e7d9965b5b2fa189b5a84c66a8252b3432c0293f1350c15ad159447ee1"

do_install () {
	install -d ${D}${datadir}/fonts/X11/TTF/
	cd ..
	for i in *.ttf; do
		install -m 0644 $i ${D}${prefix}/share/fonts/X11/TTF/${i}
	done
}

PACKAGES = "${PN}"
FILES_${PN} += "${datadir}"

pkg_postinst_${PN} () {
        set -x
        for fontdir in `find $D/usr/lib/X11/fonts -type d`; do
                mkfontdir $fontdir
                mkfontscale $fontdir
        done
        for fontdir in `find $D/usr/share/fonts/X11 -type d`; do
                mkfontdir $fontdir
                mkfontscale $fontdir
        done
}

