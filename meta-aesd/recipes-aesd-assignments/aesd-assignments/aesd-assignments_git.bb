# See https://git.yoctoproject.org/poky/tree/meta/files/common-licenses
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit update-rc.d

SRC_URI = "git://git@github.com/cu-ecen-aeld/assignments-3-and-later-TheFakeMontyOnTheRun.git;protocol=ssh;branch=main"

PV = "1.0+git${SRCPV}"

SRCREV = "b1fb21b99d6eb86cde7330787d724a3f8b1b0f20"

S = "${WORKDIR}/git/server"

FILES:${PN} += "${bindir}/aesdsocket ${sysconfdir}/init.d/aesdsocket-start-stop"
INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME:${PN} = "aesdsocket-start-stop"

TARGET_LDFLAGS += "-pthread -lrt"

do_configure () {
	:
}

do_compile () {
	oe_runmake
}

do_install () {
        install -d ${D}${bindir}
        install -m 0755 ${S}/aesdsocket ${D}${bindir}/aesdsocket
        install -d ${D}${sysconfdir}/init.d
        install -m 0755 ${S}/aesdsocket-start-stop ${D}${sysconfdir}/init.d/aesdsocket-start-stop
}
