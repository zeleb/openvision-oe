require openvision-image.bb

BB_HASH_IGNORE_MISMATCH = "1"

KERNEL_WIFI_DRIVERS += "\
	firmware-carl9170 \
	firmware-htc7010 \
	firmware-htc9271 \
	firmware-rt2870 \
	firmware-rt73 \
	firmware-rtl8712u \
	firmware-zd1211 \
	${@bb.utils.contains_any("MACHINE", "k1plus k1pro k2pro k2prov2 k3pro cube su980 alien5", "", "kernel-module-ath9k-htc kernel-module-carl9170 kernel-module-r8712u", d)} \
	${@bb.utils.contains_any("MACHINE", "k1plus k1pro k2pro k2prov2 k3pro cube su980 alien5", "", "kernel-module-rtl8187 kernel-module-zd1211rw", d)} \
    "

EXTRA_KERNEL_WIFI_DRIVERS += "\
	firmware-rtl8188eu \
	firmware-rtl8192cu \
	${@bb.utils.contains_any("MACHINE", "k1plus k1pro k2pro k2prov2 k3pro cube wetekplay wetekplay2 wetekhub x8hp su980", "", "kernel-module-r8188eu", d)} \
	${@bb.utils.contains_any("MACHINE", "k1plus k1pro k2pro k2prov2 k3pro cube wetekplay wetekplay2 wetekhub odroidc2 su980 x8hp", "", "kernel-module-rtl8192cu", d)} \
	"

BLINDSCAN_CHECK = "${@bb.utils.contains("MACHINE_FEATURES", "dreambox", "enigma2-plugin-systemplugins-dmblindscan", "enigma2-plugin-systemplugins-blindscan", d)}"

ENIGMA2_PLUGINS += "\
	enigma2-plugin-extensions-audiosync \
	${@bb.utils.contains_any("MACHINE_FEATURES", "smallflash middleflash nogamma rpi-vision", "", "enigma2-plugin-extensions-backupsuite", d)} \
	${@bb.utils.contains_any("MACHINE_FEATURES", "smallflash middleflash", "enigma2-plugin-extensions-cacheflush", "", d)} \
	enigma2-plugin-extensions-cutlisteditor \
	${@bb.utils.contains_any("MACHINE_FEATURES", "smallflash middleflash multilib", "", "enigma2-plugin-extensions-e2iplayer", d)} \
	enigma2-plugin-extensions-graphmultiepg \
	enigma2-plugin-extensions-mediaplayer \
	enigma2-plugin-extensions-mediascanner \
	enigma2-plugin-extensions-moviecut \
	enigma2-plugin-extensions-openwebif-vision \
	enigma2-plugin-extensions-pictureplayer \
	${@bb.utils.contains_any("MACHINE_FEATURES", "smallflash middleflash", "enigma2-plugin-extensions-pluginskinmover", "", d)} \
	enigma2-plugin-extensions-socketmmi \
	enigma2-plugin-skins-pli-hd \
	${@bb.utils.contains("MACHINE_FEATURES", "dvb-c", "enigma2-plugin-systemplugins-cablescan" , "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "ci", "enigma2-plugin-systemplugins-commoninterfaceassignment", "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "blindscan-dvbs", "${BLINDSCAN_CHECK}" , "", d)} \
	enigma2-plugin-systemplugins-fastscan \
	enigma2-plugin-systemplugins-hdmicec \
	enigma2-plugin-systemplugins-hotplug \
	enigma2-plugin-systemplugins-networkbrowser \
	enigma2-plugin-systemplugins-osdpositionsetup \
	enigma2-plugin-systemplugins-positionersetup \
	enigma2-plugin-systemplugins-satfinder \
	enigma2-plugin-systemplugins-softwaremanager \
	${@bb.utils.contains_any("MACHINE_FEATURES", "7seg 7segment", "enigma2-plugin-systemplugins-vfdcontrol", "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "videoenhancement", "enigma2-plugin-systemplugins-videoenhancement", "", d)} \
	enigma2-plugin-systemplugins-videomode \
	enigma2-plugin-systemplugins-videotune \
	enigma2-plugin-systemplugins-wirelesslan \
	${@bb.utils.contains("MACHINE_FEATURES", "smallflash", "", " \
	enigma2-plugin-extensions-autobackup \
	enigma2-plugin-extensions-tmbd \
	enigma2-plugin-extensions-epgimport \
	enigma2-plugin-extensions-epgrefresh \
	enigma2-plugin-extensions-reconstructapsc \
	enigma2-plugin-skins-octetfhd \
	enigma2-plugin-softcams-oscam \
	enigma2-plugin-systemplugins-mountmanager \
	enigma2-plugin-systemplugins-osd3dsetup \
	enigma2-plugin-systemplugins-terrestrialscan", d)} \
	${@bb.utils.contains_any("MACHINE_FEATURES", "smallflash middleflash", "", " \
	enigma2-plugin-extensions-filecommander \
	enigma2-plugin-extensions-keyadder \
	enigma2-plugin-extensions-openwebif-vision-terminal \
	enigma2-plugin-systemplugins-autobouquetsmaker \
	enigma2-plugin-systemplugins-serviceapp", d)} \
	"

DEPENDS += "\
	${@bb.utils.contains("MACHINE_FEATURES", "blindscan-tbs", "blindscan-s2" , "", d)} \
	enigma2 \
	enigma2-alliance-plugins \
	enigma2-locale-meta \
	enigma2-persianempire-plugins \
	enigma2-plugins \
	"

# These machine feature related plugins should not be enabled for smallflash STBs as there isn't enough space for them!
MACHINE_FEATURE_RELATED_PLUGINS += "\
	${EXTRA_KERNEL_WIFI_DRIVERS} \
	${KERNEL_WIFI_DRIVERS} \
	${@bb.utils.contains("MACHINE_FEATURES", "bluetooth", "enigma2-plugin-extensions-btdevicesmanager", "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "dvd", "enigma2-plugin-extensions-cdinfo enigma2-plugin-extensions-dvdplayer", "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "nogamma", "enigma2-plugin-extensions-rcuselect", "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "multitranscoding", "enigma2-plugin-systemplugins-multitranscodingsetup", "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "satip", "enigma2-plugin-systemplugins-satipclient" , "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "dvd", "cdtextinfo", "", d)} \
	${@bb.utils.contains_any("MACHINE_FEATURES", "streamproxy transcoding multitranscoding", "streamproxy", "", d)} \
	"

IMAGE_INSTALL += "\
	aio-grab \
	cloudflare-dns \
	cronie \
	enigma2 \
	enigma2-locale-meta \
	${ENIGMA2_PLUGINS} \
	${@bb.utils.contains("DEVELOPER_NAME", "persianprince", "enigma2-plugin-extensions-persianpalace", "", d)} \
	libavahi-client \
	libcrypto-compat \
	settings-autorestore \
	tuxbox-links \
	wget \
	${@bb.utils.contains("MACHINE_FEATURES", "smallflash", "", " \
	${MACHINE_FEATURE_RELATED_PLUGINS} \
	ntp", d)} \
	${@bb.utils.contains_any("MACHINE_FEATURES", "smallflash middleflash", "", " \
	curl \
	nfs-utils \
	openssh-sftp-server \
	samba-base", d)} \
	"

export IMAGE_BASENAME = "openvision-enigma2"
