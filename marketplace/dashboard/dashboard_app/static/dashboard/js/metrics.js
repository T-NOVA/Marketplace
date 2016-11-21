var glob_vnf_metrics = {
    "vTC": [
        {
            "metric": "total_packets",
            "desc": "Incoming and outgoing packets per second",
            "unit": "INT"
        },
        {
            "metric": "link_errors",
            "desc": "Errors in the link",
            "unit": "INT"
        },
        {
            "metric": "dropped_packets",
            "desc": "Errors in the link",
            "unit": "INT"
        },
        {
            "metric": "flows_active",
            "desc": "Flows Active",
            "unit": "INT"
        },
        {
            "metric": "memory",
            "desc": "Memory Utilized",
            "unit": "INT"
        },
        {
            "metric": "cpu",
            "desc": "CPU Utilization",
            "unit": "INT"
        }
    ],
    "vSBC": [
        {
            "metric": "total_sip_sessions",
            "desc": "Total SIP Sessions",
            "unit": "INT"
        },
        {
            "metric": "rtp_pack_in",
            "desc": "RTP pack IN",
            "unit": "INT"
        },
        {
            "metric": "rtp_pack_out",
            "desc": "RTP pack OUT",
            "unit": "INT"
        },
        {
            "metric": "rtp_pack_in_byte",
            "desc": "RTP pack in byte IN",
            "unit": "Byte"
        },
        {
            "metric": "rtp_pack_out_byte",
            "desc": "RTP pack in byte OUT",
            "unit": "Byte"
        },
        {
            "metric": "rtp_frame_loss",
            "desc": "RTP frame loss",
            "unit": "INT"
        },
        {
            "metric": "average_latency",
            "desc": "Average Latency (RTP delay)",
            "unit": "Msec"
        },
        {
            "metric": "max_latency",
            "desc": "Max Latency (RTP delay)",
            "unit": "Msec"
        },
        {
            "metric": "average_interarrival_jitter",
            "desc": "Average Interarrival Jitter",
            "unit": "Msec"
        },
        {
            "metric": "max_interarrival_jitter",
            "desc": "Max Interarrival Jitter",
            "unit": "Msec"
        },
        {
            "metric": "number_of_in_transcoding",
            "desc": "Number of IN Transcoding",
            "unit": "INT"
        },
        {
            "metric": "number_of_out_transcoding",
            "desc": "Number of OUT Transcoding",
            "unit": "INT"
        },
        {
            "metric": "number_of_in_transrating",
            "desc": "Number of OUT Transrating",
            "unit": "INT"
        },
        {
            "metric": "number_of_out_transrating",
            "desc": "Number of OUT Transrating",
            "unit": "INT"
        }
    ],
    "vTU": [
        {
            "metric": "memory",
            "desc": "Memory Free",
            "unit": "%"
        },
        {
            "metric": "cpu",
            "desc": "Storage Free",
            "unit": "%"
        }
    ],
    "vHG": [
        {
            "metric": "transcoding_score",
            "desc": "Transcoding Score",
            "unit": "INT"
        },
        {
            "metric": "distributed_storage_free_space",
            "desc": "Distributed storage free space",
            "unit": "%"
        }
    ],
    "vSA": [
        {
            "metric": "vsa_pfsense_cpu",
            "desc": "Cpu usage of pfsense",
            "unit": "FLOAT"
        },
        {
            "metric": "vsa_pfsense_mem",
            "desc": "Memory usage of  pfsense",
            "unit": "FLOAT"
        },
        {
            "metric": "vsa_pfsense_dis",
            "desc": "Hardware usage of pfsense",
            "unit": "FLOAT"
        },
        {
            "metric": "vsa_pfsense_load_avg",
            "desc": "Average load of pfsense",
            "unit": "FLOAT"
        },
        {
            "metric": "vsa_pfsense_pfstate",
            "desc": "state table size of pfsense",
            "unit": "FLOAT"
        },
        {
            "metric": "vsa_pfsense_uptime",
            "desc": "Up time of pfsense",
            "unit": "STRING"
        },
        {
            "metric": "vsa_snort_cpu",
            "desc": "Cpu usage of snort",
            "unit": "FLOAT"
        },
        {
            "metric": "vsa_snort_memory",
            "desc": "Memory usage of snort",
            "unit": "FLOAT"
        },
        {
            "metric": "vsa_snort_pkt_drop_percent",
            "desc": "Percent of the dropped packets, generate by snort",
            "unit": "INT"
        },
        {
            "metric": "vsa_snort_alerts_per_second",
            "desc": "The number of alerts per second, that are made by snort",
            "unit": "INT"
        },
        {
            "metric": "vsa_snort_kpackets_per_sec.realtime",
            "desc": "How many thousands of Packets per second in realtime through  vsa, generate by snort",
            "unit": "FLOAT"
        },
        {
            "metric": "vsa_pfsense_lan_inerrs",
            "desc": "Number of errors coming in of the lan interface of pfsense",
            "unit": "INT"
        },
        {
            "metric": "vsa_pfsense_lan_outerrs",
            "desc": "Number of errors coming out of the lan interface of pfsense",
            "unit": "INT"
        },
        {
            "metric": "vsa_pfsense_wan_inerrs",
            "desc": "Number of errors coming in of the wan interface of pfsense",
            "unit": "INT"
        },
        {
            "metric": "vsa_pfsense_wan_outerrs",
            "desc": "Number of errors coming out of the wan interface of pfsense",
            "unit": "INT"
        },
        {
            "metric": "vsa_pfsense_lan_inbytes",
            "desc": "Number of bytes coming in of the lan interface of pfsense",
            "unit": "INT"
        },
        {
            "metric": "vsa_pfsense_lan_outbytes",
            "desc": "Number of bytes coming out of the lan interface of pfsense",
            "unit": "INT"
        },
        {
            "metric": "vsa_pfsense_wan_inbytes",
            "desc": "Number of bytes coming in of the wan interface of pfsense",
            "unit": "INT"
        },
        {
            "metric": "vsa_pfsense_wan_outbytes",
            "desc": "Number of bytes coming out of the wan interface of pfsense",
            "unit": "INT"
        },
        {
            "metric": "vsa_pfsense_lan_inpkts",
            "desc": "Number of packets  coming in of the lan interface of pfsense",
            "unit": "INT"
        },
        {
            "metric": "vsa_pfsense_lan_outpkts",
            "desc": "Number of packets  coming out of the lan interface of pfsense",
            "unit": "INT"
        },
        {
            "metric": "vsa_pfsense_wan_inpkts",
            "desc": "Number of packets  coming in of the wan interface of pfsense",
            "unit": "INT"
        },
        {
            "metric": "vsa_pfsense_wan_outpkts",
            "desc": "Number of packets  coming out of the wan interface of pfsense",
            "unit": "INT"
        }
    ],
    "vPXAAS": [
        {
            "metric": "httpnum",
            "desc": "Number of HTTP requests received by Squid",
            "unit": "INT"
        },
        {
            "metric": "hits",
            "desc": "Cache hits percentage of all requests for the last 5 minutes",
            "unit": "%"
        },
        {
            "metric": "hits_bytes",
            "desc": "Cache hits percentage of bytes sent for the last 5 minutes",
            "unit": "%"
        },
        {
            "metric": "memoryhits",
            "desc": "Memory hits percentage for the last 5 minutes (hits that are logged as TCP_MEM_HIT)",
            "unit": "%"
        },
        {
            "metric": "diskhits",
            "desc": "Disk hits percentage for the last 5 minutes (hits that are logged as TCP_HIT)",
            "unit": "%"
        },
        {
            "metric": "cachediskutilization",
            "desc": "Cache disk utilization",
            "unit": "%"
        },
        {
            "metric": "cachememkutilization",
            "desc": "Cache memory utilization",
            "unit": "%"
        },
        {
            "metric": "usernum",
            "desc": "Number of users accessing the proxy",
            "unit": "INT"
        },
        {
            "metric": "cpuusage",
            "desc": "CPU consumed by Squid for the last 5 minutes",
            "unit": "%"
        }
    ]
};