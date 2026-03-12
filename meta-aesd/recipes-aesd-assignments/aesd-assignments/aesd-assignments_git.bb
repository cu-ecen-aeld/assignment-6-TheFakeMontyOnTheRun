# See https://git.yoctoproject.org/poky/tree/meta/files/common-licenses
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit update-rc.d

SRC_URI = "git://git@github.com/cu-ecen-aeld/assignments-3-and-later-TheFakeMontyOnTheRun.git;protocol=ssh;branch=main"

PV = "1.0+git${SRCPV}"

SRCREV = "6d376fea42c4cef52d182b2bbe16bed0da20ceec"

S = "${WORKDIR}/git/server"

FILES:${PN} += "${bindir}/aesdsocket"
FILES:${PN} += "${sysconfdir}/init.d/aesdsocket-start-stop"
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
