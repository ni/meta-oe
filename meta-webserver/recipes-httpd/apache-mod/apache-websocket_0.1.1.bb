SUMMARY = "Websocket module for Apache web server"
DESCRIPTION = "Process requests using the WebSocket protocol (RFC 6455)"
HOMEPAGE = "https://github.com/jchampio/${PN}/"
SECTION = "net"
LICENSE = "Apache-2.0"

APACHE_PACKAGE_NAME = "apache2"
DEPENDS = "${APACHE_PACKAGE_NAME} ${APACHE_PACKAGE_NAME}-native"
RDEPENDS_${PN} += "${APACHE_PACKAGE_NAME}"

# Original repo (https://github.com/disconnect/apache-websocket/) was
# abandoned in 2012.
SRC_URI = "http://github.com/jchampio/${PN}/archive/${PV}.tar.gz;downloadfilename=${PN}-${PV}.tar.gz"

SRC_URI[sha256sum] = "794640b091d65a26d9ad638e41d082a6f9d04ff902d8dff5dfe12add14b8fca7"
SRC_URI[md5sum] = "d61e1c207f3a33f7703eedc121cfbc28"

LIC_FILES_CHKSUM = "file://LICENSE;md5=2ee41112a44fe7014dce33e26468ba93"

inherit autotools-brokensep

do_install() {
    install -d ${D}${libdir}/${APACHE_PACKAGE_NAME}/modules/
    install ${B}/.libs/mod_websocket.so ${D}${libdir}/${APACHE_PACKAGE_NAME}/modules/
}

FILES_${PN} += " ${libdir}/${APACHE_PACKAGE_NAME}/modules/* "
FILES_${PN}-dbg += " ${libdir}/${APACHE_PACKAGE_NAME}/modules/.debug/* "
