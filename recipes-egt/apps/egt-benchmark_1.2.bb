DESCRIPTION = "Microchip EGT Benchmark Application"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://COPYING;endline=202;md5=3b83ef96387f14655fc854ddc3c6bd57"

DEPENDS = "libegt"

SRC_URI = "gitsm://github.com/linux4sam/egt-benchmark.git;protocol=https;branch=master"

SRCREV = "004238de9879d1ce8c74b34e0c397c768140bc4c"

S = "${WORKDIR}/git"

inherit pkgconfig autotools-brokensep siteinfo

EXTRA_OECONF += "--program-prefix='egt_'"

do_configure:prepend() {
	( cd ${S} && ${S}/autogen.sh )
}

FILES:${PN} += " \
    ${datadir}/egt/* \
"

python __anonymous () {
    endianness = d.getVar('SITEINFO_ENDIANNESS')
    if endianness == 'be':
        raise bb.parse.SkipRecipe('Requires little-endian target.')
}
