SUMMARY = "PT Sans Fonts"
DESCRIPTION = "The PT Sans TTF font set"
HOMEPAGE = "http://www.paratype.com/public/"
BUGTRACKER = "n/a"

SECTION = "x11/fonts"

LICENSE = "OFL-1.1"
LIC_FILES_CHKSUM = "file://../license-destdir/ttf-pt-sans/generic_OFL-1.1;md5=fac3a519e5e9eb96316656e0ca4f2b90"

inherit allarch

RDEPENDS_${PN} = "fontconfig-utils"
PR = "1"

inherit fontcache

FONT_PACKAGES = "${PN}"

SRC_URI = "http://www.fontstock.com/public/PTSansOFL.zip"

SRC_URI[md5sum] = "e5b99133d3b72cd35400b5aa810ad0ee"
SRC_URI[sha256sum] = "57448741b709c5f022127134ffd49506e3925242bd06f73a039e070765d1d637"


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

