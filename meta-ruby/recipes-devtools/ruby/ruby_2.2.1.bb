require ruby.inc

SRC_URI[md5sum] = "b49fc67a834e4f77249eb73eecffb1c9"
SRC_URI[sha256sum] = "5a4de38068eca8919cb087d338c0c2e3d72c9382c804fb27ab746e6c7819ab28"

# it's unknown to configure script, but then passed to extconf.rb
# maybe it's not really needed as we're hardcoding the result with
# 0001-socket-extconf-hardcode-wide-getaddr-info-test-outco.patch
UNKNOWN_CONFIGURE_WHITELIST += "--enable-wide-getaddrinfo"

PACKAGECONFIG ??= ""
PACKAGECONFIG[valgrind] = "--with-valgrind=yes, --with-valgrind=no, valgrind"
PACKAGECONFIG[gpm] = "--with-gmp=yes, --with-gmp=no, gmp"

EXTRA_OECONF = "\
    --enable-wide-getaddrinfo \
    --disable-versioned-paths \
    --disable-rpath \
    --disable-dtrace \
    --enable-shared \
    --enable-load-relative \
"

EXTRA_OEMAKE = " \
    LIBRUBYARG='-lruby-static' \
"

do_install() {
    oe_runmake 'DESTDIR=${D}' install
}

PACKAGES =+ "${PN}-ri-docs ${PN}-rdoc"

SUMMARY_${PN}-ri-docs = "ri (Ruby Interactive) documentation for the Ruby standard library"
RDEPENDS_${PN}-ri-docs = "${PN}"
FILES_${PN}-ri-docs += "${datadir}/ri"

SUMMARY_${PN}-rdoc = "RDoc documentation generator from Ruby source"
RDEPENDS_${PN}-rdoc = "${PN}"
FILES_${PN}-rdoc += "${libdir}/ruby/rdoc"

FILES_${PN} += "${datadir}/rubygems"

FILES_${PN}-dbg += "${libdir}/ruby/*/.debug \
                    ${libdir}/ruby/*/*/.debug \
                    ${libdir}/ruby/*/*/*/.debug \
                    ${libdir}/ruby/*/*/*/*/.debug"

BBCLASSEXTEND = "native"
