package com.unicorn.sxmobileoa

import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.detail.model.Detail
import com.unicorn.sxmobileoa.login.court.model.Court
import com.unicorn.sxmobileoa.login.court.network.CourtUseCase
import io.reactivex.Maybe


// TODO DELETE FAKER CLASS
class
Faker {

    private val courtJson = "[{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R00\",\"dmms\":\"陕西省高级人民法院\",\"fydz\":\"陕\",\"fyjb\":2,\"fyjc\":\"陕\",\"gfdm\":3600,\"qybz\":1,\"sjdm\":\"000\",\"sm\":\"\",\"xssx\":\"R00\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R10\",\"dmms\":\"西安市中级人民法院\",\"fydz\":\"陕01\",\"fyjb\":3,\"fyjc\":\"西中\",\"gfdm\":3601,\"qybz\":1,\"sjdm\":\"R00\",\"sm\":\"\",\"xssx\":\"R10\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R11\",\"dmms\":\"西安市新城区人民法院\",\"fydz\":\"陕0102\",\"fyjb\":4,\"fyjc\":\"新\",\"gfdm\":3602,\"qybz\":1,\"sjdm\":\"R10\",\"sm\":\"\",\"xssx\":\"R11\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R12\",\"dmms\":\"西安市碑林区人民法院\",\"fydz\":\"陕0103\",\"fyjb\":4,\"fyjc\":\"碑\",\"gfdm\":3603,\"qybz\":1,\"sjdm\":\"R10\",\"sm\":\"\",\"xssx\":\"R12\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R13\",\"dmms\":\"西安市莲湖区人民法院\",\"fydz\":\"陕0104\",\"fyjb\":4,\"fyjc\":\"莲\",\"gfdm\":3604,\"qybz\":1,\"sjdm\":\"R10\",\"sm\":\"\",\"xssx\":\"R13\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R14\",\"dmms\":\"西安市灞桥区人民法院\",\"fydz\":\"陕0111\",\"fyjb\":4,\"fyjc\":\"灞\",\"gfdm\":3605,\"qybz\":1,\"sjdm\":\"R10\",\"sm\":\"\",\"xssx\":\"R14\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R15\",\"dmms\":\"西安市未央区人民法院\",\"fydz\":\"陕0112\",\"fyjb\":4,\"fyjc\":\"未\",\"gfdm\":3606,\"qybz\":1,\"sjdm\":\"R10\",\"sm\":\"\",\"xssx\":\"R15\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R16\",\"dmms\":\"西安市雁塔区人民法院\",\"fydz\":\"陕0113\",\"fyjb\":4,\"fyjc\":\"雁\",\"gfdm\":3607,\"qybz\":1,\"sjdm\":\"R10\",\"sm\":\"\",\"xssx\":\"R16\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R17\",\"dmms\":\"西安市阎良区人民法院\",\"fydz\":\"陕0114\",\"fyjb\":4,\"fyjc\":\"阎\",\"gfdm\":3608,\"qybz\":1,\"sjdm\":\"R10\",\"sm\":\"\",\"xssx\":\"R17\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R18\",\"dmms\":\"西安市长安区人民法院\",\"fydz\":\"陕0116\",\"fyjb\":4,\"fyjc\":\"长安\",\"gfdm\":3609,\"qybz\":1,\"sjdm\":\"R10\",\"sm\":\"\",\"xssx\":\"R18\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R19\",\"dmms\":\"蓝田县人民法院\",\"fydz\":\"陕0122\",\"fyjb\":4,\"fyjc\":\"蓝\",\"gfdm\":3610,\"qybz\":1,\"sjdm\":\"R10\",\"sm\":\"\",\"xssx\":\"R19\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R1A\",\"dmms\":\"西安市临潼区人民法院\",\"fydz\":\"陕0115\",\"fyjb\":4,\"fyjc\":\"临潼\",\"gfdm\":3611,\"qybz\":1,\"sjdm\":\"R10\",\"sm\":\"\",\"xssx\":\"R1A\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R1B\",\"dmms\":\"周至县人民法院\",\"fydz\":\"陕0124\",\"fyjb\":4,\"fyjc\":\"周\",\"gfdm\":3612,\"qybz\":1,\"sjdm\":\"R10\",\"sm\":\"\",\"xssx\":\"R1B\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R1C\",\"dmms\":\"户县人民法院\",\"fydz\":\"陕0125\",\"fyjb\":4,\"fyjc\":\"户\",\"gfdm\":3613,\"qybz\":1,\"sjdm\":\"R10\",\"sm\":\"\",\"xssx\":\"R1C\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R1D\",\"dmms\":\"高陵县人民法院\",\"fydz\":\"陕0126\",\"fyjb\":4,\"fyjc\":\"高\",\"gfdm\":3614,\"qybz\":1,\"sjdm\":\"R10\",\"sm\":\"\",\"xssx\":\"R1D\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R20\",\"dmms\":\"铜川市中级人民法院\",\"fydz\":\"陕02\",\"fyjb\":3,\"fyjc\":\"铜中\",\"gfdm\":3615,\"qybz\":1,\"sjdm\":\"R00\",\"sm\":\"\",\"xssx\":\"R20\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R21\",\"dmms\":\"铜川市王益区人民法院\",\"fydz\":\"陕0202\",\"fyjb\":4,\"fyjc\":\"王\",\"gfdm\":3616,\"qybz\":1,\"sjdm\":\"R20\",\"sm\":\"\",\"xssx\":\"R21\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R22\",\"dmms\":\"铜川市印台区人民法院\",\"fydz\":\"陕0203\",\"fyjb\":4,\"fyjc\":\"印\",\"gfdm\":3617,\"qybz\":1,\"sjdm\":\"R20\",\"sm\":\"\",\"xssx\":\"R22\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R23\",\"dmms\":\"铜川市耀州区人民法院\",\"fydz\":\"陕0204\",\"fyjb\":4,\"fyjc\":\"耀\",\"gfdm\":3618,\"qybz\":1,\"sjdm\":\"R20\",\"sm\":\"\",\"xssx\":\"R23\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R24\",\"dmms\":\"宜君县人民法院\",\"fydz\":\"陕0222\",\"fyjb\":4,\"fyjc\":\"宜君\",\"gfdm\":3619,\"qybz\":1,\"sjdm\":\"R20\",\"sm\":\"\",\"xssx\":\"R24\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R30\",\"dmms\":\"宝鸡市中级人民法院\",\"fydz\":\"陕03\",\"fyjb\":3,\"fyjc\":\"宝中\",\"gfdm\":3620,\"qybz\":1,\"sjdm\":\"R00\",\"sm\":\"\",\"xssx\":\"R30\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R31\",\"dmms\":\"宝鸡市渭滨区人民法院\",\"fydz\":\"陕0302\",\"fyjb\":4,\"fyjc\":\"渭滨\",\"gfdm\":3621,\"qybz\":1,\"sjdm\":\"R30\",\"sm\":\"\",\"xssx\":\"R31\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R32\",\"dmms\":\"宝鸡市金台区人民法院\",\"fydz\":\"陕0303\",\"fyjb\":4,\"fyjc\":\"金\",\"gfdm\":3622,\"qybz\":1,\"sjdm\":\"R30\",\"sm\":\"\",\"xssx\":\"R32\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R33\",\"dmms\":\"宝鸡市陈仓区人民法院\",\"fydz\":\"陕0304\",\"fyjb\":4,\"fyjc\":\"陈\",\"gfdm\":3623,\"qybz\":1,\"sjdm\":\"R30\",\"sm\":\"\",\"xssx\":\"R33\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R34\",\"dmms\":\"凤翔县人民法院\",\"fydz\":\"陕0322\",\"fyjb\":4,\"fyjc\":\"凤翔\",\"gfdm\":3624,\"qybz\":1,\"sjdm\":\"R30\",\"sm\":\"\",\"xssx\":\"R34\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R35\",\"dmms\":\"歧山县人民法院\",\"fydz\":\"陕0323\",\"fyjb\":4,\"fyjc\":\"岐\",\"gfdm\":3625,\"qybz\":1,\"sjdm\":\"R30\",\"sm\":\"\",\"xssx\":\"R35\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R36\",\"dmms\":\"扶风县人民法院\",\"fydz\":\"陕0324\",\"fyjb\":4,\"fyjc\":\"扶\",\"gfdm\":3626,\"qybz\":1,\"sjdm\":\"R30\",\"sm\":\"\",\"xssx\":\"R36\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R37\",\"dmms\":\"眉县人民法院\",\"fydz\":\"陕0326\",\"fyjb\":4,\"fyjc\":\"眉\",\"gfdm\":3627,\"qybz\":1,\"sjdm\":\"R30\",\"sm\":\"\",\"xssx\":\"R37\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R38\",\"dmms\":\"陇县人民法院\",\"fydz\":\"陕0327\",\"fyjb\":4,\"fyjc\":\"陇\",\"gfdm\":3628,\"qybz\":1,\"sjdm\":\"R30\",\"sm\":\"\",\"xssx\":\"R38\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R39\",\"dmms\":\"千阳县人民法院\",\"fydz\":\"陕0328\",\"fyjb\":4,\"fyjc\":\"千\",\"gfdm\":3629,\"qybz\":1,\"sjdm\":\"R30\",\"sm\":\"\",\"xssx\":\"R39\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R3A\",\"dmms\":\"麟游县人民法院\",\"fydz\":\"陕0329\",\"fyjb\":4,\"fyjc\":\"麟\",\"gfdm\":3630,\"qybz\":1,\"sjdm\":\"R30\",\"sm\":\"\",\"xssx\":\"R3A\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R3B\",\"dmms\":\"凤县人民法院\",\"fydz\":\"陕0330\",\"fyjb\":4,\"fyjc\":\"凤\",\"gfdm\":3631,\"qybz\":1,\"sjdm\":\"R30\",\"sm\":\"\",\"xssx\":\"R3B\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R3C\",\"dmms\":\"太白县人民法院\",\"fydz\":\"陕0331\",\"fyjb\":4,\"fyjc\":\"太\",\"gfdm\":3632,\"qybz\":1,\"sjdm\":\"R30\",\"sm\":\"\",\"xssx\":\"R3C\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R40\",\"dmms\":\"咸阳市中级人民法院\",\"fydz\":\"陕04\",\"fyjb\":3,\"fyjc\":\"咸中\",\"gfdm\":3633,\"qybz\":1,\"sjdm\":\"R00\",\"sm\":\"\",\"xssx\":\"R40\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R41\",\"dmms\":\"咸阳市秦都区人民法院\",\"fydz\":\"陕0402\",\"fyjb\":4,\"fyjc\":\"秦\",\"gfdm\":3634,\"qybz\":1,\"sjdm\":\"R40\",\"sm\":\"\",\"xssx\":\"R41\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R42\",\"dmms\":\"咸阳市杨陵区人民法院\",\"fydz\":\"陕0403\",\"fyjb\":4,\"fyjc\":\"杨\",\"gfdm\":3635,\"qybz\":1,\"sjdm\":\"R40\",\"sm\":\"\",\"xssx\":\"R42\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R43\",\"dmms\":\"咸阳市渭城区人民法院\",\"fydz\":\"陕0404\",\"fyjb\":4,\"fyjc\":\"渭城\",\"gfdm\":3636,\"qybz\":1,\"sjdm\":\"R40\",\"sm\":\"\",\"xssx\":\"R43\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R44\",\"dmms\":\"兴平市人民法院\",\"fydz\":\"陕0481\",\"fyjb\":4,\"fyjc\":\"兴\",\"gfdm\":3637,\"qybz\":1,\"sjdm\":\"R40\",\"sm\":\"\",\"xssx\":\"R44\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R45\",\"dmms\":\"三原县人民法院\",\"fydz\":\"陕0422\",\"fyjb\":4,\"fyjc\":\"三\",\"gfdm\":3638,\"qybz\":1,\"sjdm\":\"R40\",\"sm\":\"\",\"xssx\":\"R45\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R46\",\"dmms\":\"泾阳县人民法院\",\"fydz\":\"陕0423\",\"fyjb\":4,\"fyjc\":\"泾\",\"gfdm\":3639,\"qybz\":1,\"sjdm\":\"R40\",\"sm\":\"\",\"xssx\":\"R46\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R47\",\"dmms\":\"乾县人民法院\",\"fydz\":\"陕0424\",\"fyjb\":4,\"fyjc\":\"乾\",\"gfdm\":3640,\"qybz\":1,\"sjdm\":\"R40\",\"sm\":\"\",\"xssx\":\"R47\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R48\",\"dmms\":\"礼泉县人民法院\",\"fydz\":\"陕0425\",\"fyjb\":4,\"fyjc\":\"礼\",\"gfdm\":3641,\"qybz\":1,\"sjdm\":\"R40\",\"sm\":\"\",\"xssx\":\"R48\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R49\",\"dmms\":\"永寿县人民法院\",\"fydz\":\"陕0426\",\"fyjb\":4,\"fyjc\":\"永\",\"gfdm\":3642,\"qybz\":1,\"sjdm\":\"R40\",\"sm\":\"\",\"xssx\":\"R49\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R4A\",\"dmms\":\"彬县人民法院\",\"fydz\":\"陕0427\",\"fyjb\":4,\"fyjc\":\"彬\",\"gfdm\":3643,\"qybz\":1,\"sjdm\":\"R40\",\"sm\":\"\",\"xssx\":\"R4A\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R4B\",\"dmms\":\"长武县人民法院\",\"fydz\":\"陕0428\",\"fyjb\":4,\"fyjc\":\"长武\",\"gfdm\":3644,\"qybz\":1,\"sjdm\":\"R40\",\"sm\":\"\",\"xssx\":\"R4B\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R4C\",\"dmms\":\"旬邑县人民法院\",\"fydz\":\"陕0429\",\"fyjb\":4,\"fyjc\":\"旬邑\",\"gfdm\":3645,\"qybz\":1,\"sjdm\":\"R40\",\"sm\":\"\",\"xssx\":\"R4C\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R4D\",\"dmms\":\"淳化县人民法院\",\"fydz\":\"陕0430\",\"fyjb\":4,\"fyjc\":\"淳\",\"gfdm\":3646,\"qybz\":1,\"sjdm\":\"R40\",\"sm\":\"\",\"xssx\":\"R4D\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R4E\",\"dmms\":\"武功县人民法院\",\"fydz\":\"陕0431\",\"fyjb\":4,\"fyjc\":\"武\",\"gfdm\":3647,\"qybz\":1,\"sjdm\":\"R40\",\"sm\":\"\",\"xssx\":\"R4E\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R50\",\"dmms\":\"渭南市中级人民法院\",\"fydz\":\"陕05\",\"fyjb\":3,\"fyjc\":\"渭中\",\"gfdm\":3648,\"qybz\":1,\"sjdm\":\"R00\",\"sm\":\"\",\"xssx\":\"R50\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R51\",\"dmms\":\"渭南市临渭区人民法院\",\"fydz\":\"陕0502\",\"fyjb\":4,\"fyjc\":\"临渭\",\"gfdm\":3649,\"qybz\":1,\"sjdm\":\"R50\",\"sm\":\"\",\"xssx\":\"R51\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R52\",\"dmms\":\"韩城市人民法院\",\"fydz\":\"陕0581\",\"fyjb\":4,\"fyjc\":\"韩\",\"gfdm\":3650,\"qybz\":1,\"sjdm\":\"R50\",\"sm\":\"\",\"xssx\":\"R52\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R53\",\"dmms\":\"华阴市人民法院\",\"fydz\":\"陕0582\",\"fyjb\":4,\"fyjc\":\"华阴\",\"gfdm\":3651,\"qybz\":1,\"sjdm\":\"R50\",\"sm\":\"\",\"xssx\":\"R53\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R54\",\"dmms\":\"华县人民法院\",\"fydz\":\"陕0521\",\"fyjb\":4,\"fyjc\":\"华\",\"gfdm\":3652,\"qybz\":1,\"sjdm\":\"R50\",\"sm\":\"\",\"xssx\":\"R54\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R55\",\"dmms\":\"潼关县人民法院\",\"fydz\":\"陕0522\",\"fyjb\":4,\"fyjc\":\"潼\",\"gfdm\":3653,\"qybz\":1,\"sjdm\":\"R50\",\"sm\":\"\",\"xssx\":\"R55\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R56\",\"dmms\":\"大荔县人民法院\",\"fydz\":\"陕0523\",\"fyjb\":4,\"fyjc\":\"大\",\"gfdm\":3654,\"qybz\":1,\"sjdm\":\"R50\",\"sm\":\"\",\"xssx\":\"R56\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R57\",\"dmms\":\"蒲城县人民法院\",\"fydz\":\"陕0526\",\"fyjb\":4,\"fyjc\":\"蒲\",\"gfdm\":3655,\"qybz\":1,\"sjdm\":\"R50\",\"sm\":\"\",\"xssx\":\"R57\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R58\",\"dmms\":\"澄城县人民法院\",\"fydz\":\"陕0525\",\"fyjb\":4,\"fyjc\":\"澄\",\"gfdm\":3656,\"qybz\":1,\"sjdm\":\"R50\",\"sm\":\"\",\"xssx\":\"R58\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R59\",\"dmms\":\"白水县人民法院\",\"fydz\":\"陕0527\",\"fyjb\":4,\"fyjc\":\"白水\",\"gfdm\":3657,\"qybz\":1,\"sjdm\":\"R50\",\"sm\":\"\",\"xssx\":\"R59\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R5A\",\"dmms\":\"合阳县人民法院\",\"fydz\":\"陕0524\",\"fyjb\":4,\"fyjc\":\"合\",\"gfdm\":3658,\"qybz\":1,\"sjdm\":\"R50\",\"sm\":\"\",\"xssx\":\"R5A\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R5B\",\"dmms\":\"富平县人民法院\",\"fydz\":\"陕0528\",\"fyjb\":4,\"fyjc\":\"富平\",\"gfdm\":3659,\"qybz\":1,\"sjdm\":\"R50\",\"sm\":\"\",\"xssx\":\"R5B\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R60\",\"dmms\":\"汉中市中级人民法院\",\"fydz\":\"陕07\",\"fyjb\":3,\"fyjc\":\"汉中\",\"gfdm\":3660,\"qybz\":1,\"sjdm\":\"R00\",\"sm\":\"\",\"xssx\":\"R60\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R61\",\"dmms\":\"汉中市汉台区人民法院\",\"fydz\":\"陕0702\",\"fyjb\":4,\"fyjc\":\"汉台\",\"gfdm\":3661,\"qybz\":1,\"sjdm\":\"R60\",\"sm\":\"\",\"xssx\":\"R61\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R62\",\"dmms\":\"南郑县人民法院\",\"fydz\":\"陕0721\",\"fyjb\":4,\"fyjc\":\"南\",\"gfdm\":3662,\"qybz\":1,\"sjdm\":\"R60\",\"sm\":\"\",\"xssx\":\"R62\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R63\",\"dmms\":\"城固县人民法院\",\"fydz\":\"陕0722\",\"fyjb\":4,\"fyjc\":\"城\",\"gfdm\":3663,\"qybz\":1,\"sjdm\":\"R60\",\"sm\":\"\",\"xssx\":\"R63\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R64\",\"dmms\":\"洋县人民法院\",\"fydz\":\"陕0723\",\"fyjb\":4,\"fyjc\":\"洋\",\"gfdm\":3664,\"qybz\":1,\"sjdm\":\"R60\",\"sm\":\"\",\"xssx\":\"R64\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R65\",\"dmms\":\"西乡县人民法院\",\"fydz\":\"陕0724\",\"fyjb\":4,\"fyjc\":\"西\",\"gfdm\":3665,\"qybz\":1,\"sjdm\":\"R60\",\"sm\":\"\",\"xssx\":\"R65\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R66\",\"dmms\":\"勉县人民法院\",\"fydz\":\"陕0725\",\"fyjb\":4,\"fyjc\":\"勉\",\"gfdm\":3666,\"qybz\":1,\"sjdm\":\"R60\",\"sm\":\"\",\"xssx\":\"R66\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R67\",\"dmms\":\"宁强县人民法院\",\"fydz\":\"陕0726\",\"fyjb\":4,\"fyjc\":\"宁强\",\"gfdm\":3667,\"qybz\":1,\"sjdm\":\"R60\",\"sm\":\"\",\"xssx\":\"R67\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R68\",\"dmms\":\"略阳县人民法院\",\"fydz\":\"陕0727\",\"fyjb\":4,\"fyjc\":\"略\",\"gfdm\":3668,\"qybz\":1,\"sjdm\":\"R60\",\"sm\":\"\",\"xssx\":\"R68\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R69\",\"dmms\":\"镇巴县人民法院\",\"fydz\":\"陕0728\",\"fyjb\":4,\"fyjc\":\"镇巴\",\"gfdm\":3669,\"qybz\":1,\"sjdm\":\"R60\",\"sm\":\"\",\"xssx\":\"R69\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R6A\",\"dmms\":\"留坝县人民法院\",\"fydz\":\"陕0729\",\"fyjb\":4,\"fyjc\":\"留\",\"gfdm\":3670,\"qybz\":1,\"sjdm\":\"R60\",\"sm\":\"\",\"xssx\":\"R6A\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R6B\",\"dmms\":\"佛坪县人民法院\",\"fydz\":\"陕0730\",\"fyjb\":4,\"fyjc\":\"佛\",\"gfdm\":3671,\"qybz\":1,\"sjdm\":\"R60\",\"sm\":\"\",\"xssx\":\"R6B\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R70\",\"dmms\":\"安康市中级人民法院\",\"fydz\":\"陕09\",\"fyjb\":3,\"fyjc\":\"安中\",\"gfdm\":3672,\"qybz\":1,\"sjdm\":\"R00\",\"sm\":\"\",\"xssx\":\"R70\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R71\",\"dmms\":\"汉滨区人民法院\",\"fydz\":\"陕0902\",\"fyjb\":4,\"fyjc\":\"汉滨\",\"gfdm\":3673,\"qybz\":1,\"sjdm\":\"R70\",\"sm\":\"\",\"xssx\":\"R71\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R72\",\"dmms\":\"汉阴县人民法院\",\"fydz\":\"陕0921\",\"fyjb\":4,\"fyjc\":\"汉阴\",\"gfdm\":3674,\"qybz\":1,\"sjdm\":\"R70\",\"sm\":\"\",\"xssx\":\"R72\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R73\",\"dmms\":\"石泉县人民法院\",\"fydz\":\"陕0922\",\"fyjb\":4,\"fyjc\":\"石\",\"gfdm\":3675,\"qybz\":1,\"sjdm\":\"R70\",\"sm\":\"\",\"xssx\":\"R73\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R74\",\"dmms\":\"宁陕县人民法院\",\"fydz\":\"陕0923\",\"fyjb\":4,\"fyjc\":\"宁陕\",\"gfdm\":3676,\"qybz\":1,\"sjdm\":\"R70\",\"sm\":\"\",\"xssx\":\"R74\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R75\",\"dmms\":\"紫阳县人民法院\",\"fydz\":\"陕0924\",\"fyjb\":4,\"fyjc\":\"紫\",\"gfdm\":3677,\"qybz\":1,\"sjdm\":\"R70\",\"sm\":\"\",\"xssx\":\"R75\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R76\",\"dmms\":\"岚皋县人民法院\",\"fydz\":\"陕0925\",\"fyjb\":4,\"fyjc\":\"岚\",\"gfdm\":3678,\"qybz\":1,\"sjdm\":\"R70\",\"sm\":\"\",\"xssx\":\"R76\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R77\",\"dmms\":\"平利县人民法院\",\"fydz\":\"陕0926\",\"fyjb\":4,\"fyjc\":\"平\",\"gfdm\":3679,\"qybz\":1,\"sjdm\":\"R70\",\"sm\":\"\",\"xssx\":\"R77\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R78\",\"dmms\":\"镇坪县人民法院\",\"fydz\":\"陕0927\",\"fyjb\":4,\"fyjc\":\"镇坪\",\"gfdm\":3680,\"qybz\":1,\"sjdm\":\"R70\",\"sm\":\"\",\"xssx\":\"R78\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R79\",\"dmms\":\"旬阳县人民法院\",\"fydz\":\"陕0928\",\"fyjb\":4,\"fyjc\":\"旬阳\",\"gfdm\":3681,\"qybz\":1,\"sjdm\":\"R70\",\"sm\":\"\",\"xssx\":\"R79\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R7A\",\"dmms\":\"白河县人民法院\",\"fydz\":\"陕0929\",\"fyjb\":4,\"fyjc\":\"白河\",\"gfdm\":3682,\"qybz\":1,\"sjdm\":\"R70\",\"sm\":\"\",\"xssx\":\"R7A\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R80\",\"dmms\":\"商洛市中级人民法院\",\"fydz\":\"陕10\",\"fyjb\":3,\"fyjc\":\"商中\",\"gfdm\":3683,\"qybz\":1,\"sjdm\":\"R00\",\"sm\":\"\",\"xssx\":\"R80\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R81\",\"dmms\":\"商州区人民法院\",\"fydz\":\"陕1002\",\"fyjb\":4,\"fyjc\":\"商州\",\"gfdm\":3684,\"qybz\":1,\"sjdm\":\"R80\",\"sm\":\"\",\"xssx\":\"R81\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R82\",\"dmms\":\"洛南县人民法院\",\"fydz\":\"陕1021\",\"fyjb\":4,\"fyjc\":\"洛南\",\"gfdm\":3685,\"qybz\":1,\"sjdm\":\"R80\",\"sm\":\"\",\"xssx\":\"R82\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R83\",\"dmms\":\"丹凤县人民法院\",\"fydz\":\"陕1022\",\"fyjb\":4,\"fyjc\":\"丹\",\"gfdm\":3686,\"qybz\":1,\"sjdm\":\"R80\",\"sm\":\"\",\"xssx\":\"R83\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R84\",\"dmms\":\"商南县人民法院\",\"fydz\":\"陕1023\",\"fyjb\":4,\"fyjc\":\"商南\",\"gfdm\":3687,\"qybz\":1,\"sjdm\":\"R80\",\"sm\":\"\",\"xssx\":\"R84\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R85\",\"dmms\":\"山阳县人民法院\",\"fydz\":\"陕1024\",\"fyjb\":4,\"fyjc\":\"山\",\"gfdm\":3688,\"qybz\":1,\"sjdm\":\"R80\",\"sm\":\"\",\"xssx\":\"R85\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R86\",\"dmms\":\"镇安县人民法院\",\"fydz\":\"陕1025\",\"fyjb\":4,\"fyjc\":\"镇安\",\"gfdm\":3689,\"qybz\":1,\"sjdm\":\"R80\",\"sm\":\"\",\"xssx\":\"R86\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R87\",\"dmms\":\"柞水县人民法院\",\"fydz\":\"陕1026\",\"fyjb\":4,\"fyjc\":\"柞\",\"gfdm\":3690,\"qybz\":1,\"sjdm\":\"R80\",\"sm\":\"\",\"xssx\":\"R87\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R90\",\"dmms\":\"延安市中级人民法院\",\"fydz\":\"陕06\",\"fyjb\":3,\"fyjc\":\"延中\",\"gfdm\":3691,\"qybz\":1,\"sjdm\":\"R00\",\"sm\":\"\",\"xssx\":\"R90\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R91\",\"dmms\":\"延安市宝塔区人民法院\",\"fydz\":\"陕0602\",\"fyjb\":4,\"fyjc\":\"宝\",\"gfdm\":3692,\"qybz\":1,\"sjdm\":\"R90\",\"sm\":\"\",\"xssx\":\"R91\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R92\",\"dmms\":\"延长县人民法院\",\"fydz\":\"陕0621\",\"fyjb\":4,\"fyjc\":\"延长\",\"gfdm\":3693,\"qybz\":1,\"sjdm\":\"R90\",\"sm\":\"\",\"xssx\":\"R92\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R93\",\"dmms\":\"延川县人民法院\",\"fydz\":\"陕0622\",\"fyjb\":4,\"fyjc\":\"延川\",\"gfdm\":3694,\"qybz\":1,\"sjdm\":\"R90\",\"sm\":\"\",\"xssx\":\"R93\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R94\",\"dmms\":\"子长县人民法院\",\"fydz\":\"陕0623\",\"fyjb\":4,\"fyjc\":\"子长\",\"gfdm\":3695,\"qybz\":1,\"sjdm\":\"R90\",\"sm\":\"\",\"xssx\":\"R94\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R95\",\"dmms\":\"安塞县人民法院\",\"fydz\":\"陕0624\",\"fyjb\":4,\"fyjc\":\"安\",\"gfdm\":3696,\"qybz\":1,\"sjdm\":\"R90\",\"sm\":\"\",\"xssx\":\"R95\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R96\",\"dmms\":\"志丹县人民法院\",\"fydz\":\"陕0625\",\"fyjb\":4,\"fyjc\":\"志\",\"gfdm\":3697,\"qybz\":1,\"sjdm\":\"R90\",\"sm\":\"\",\"xssx\":\"R96\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R97\",\"dmms\":\"吴起县人民法院\",\"fydz\":\"陕0626\",\"fyjb\":4,\"fyjc\":\"吴起\",\"gfdm\":3698,\"qybz\":1,\"sjdm\":\"R90\",\"sm\":\"\",\"xssx\":\"R97\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R98\",\"dmms\":\"甘泉县人民法院\",\"fydz\":\"陕0627\",\"fyjb\":4,\"fyjc\":\"甘\",\"gfdm\":3699,\"qybz\":1,\"sjdm\":\"R90\",\"sm\":\"\",\"xssx\":\"R98\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R99\",\"dmms\":\"富县人民法院\",\"fydz\":\"陕0628\",\"fyjb\":4,\"fyjc\":\"富\",\"gfdm\":3700,\"qybz\":1,\"sjdm\":\"R90\",\"sm\":\"\",\"xssx\":\"R99\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R9A\",\"dmms\":\"洛川县人民法院\",\"fydz\":\"陕0629\",\"fyjb\":4,\"fyjc\":\"洛川\",\"gfdm\":3701,\"qybz\":1,\"sjdm\":\"R90\",\"sm\":\"\",\"xssx\":\"R9A\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R9B\",\"dmms\":\"宜川县人民法院\",\"fydz\":\"陕0630\",\"fyjb\":4,\"fyjc\":\"宜川\",\"gfdm\":3702,\"qybz\":1,\"sjdm\":\"R90\",\"sm\":\"\",\"xssx\":\"R9B\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R9C\",\"dmms\":\"黄龙县人民法院\",\"fydz\":\"陕0631\",\"fyjb\":4,\"fyjc\":\"黄龙\",\"gfdm\":3703,\"qybz\":1,\"sjdm\":\"R90\",\"sm\":\"\",\"xssx\":\"R9C\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"R9D\",\"dmms\":\"黄陵县人民法院\",\"fydz\":\"陕0632\",\"fyjb\":4,\"fyjc\":\"黄陵\",\"gfdm\":3704,\"qybz\":1,\"sjdm\":\"R90\",\"sm\":\"\",\"xssx\":\"R9D\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"RA0\",\"dmms\":\"榆林市中级人民法院\",\"fydz\":\"陕08\",\"fyjb\":3,\"fyjc\":\"榆中\",\"gfdm\":3705,\"qybz\":1,\"sjdm\":\"R00\",\"sm\":\"\",\"xssx\":\"RA0\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"RA1\",\"dmms\":\"榆林市榆阳区人民法院\",\"fydz\":\"陕0802\",\"fyjb\":4,\"fyjc\":\"榆\",\"gfdm\":3706,\"qybz\":1,\"sjdm\":\"RA0\",\"sm\":\"\",\"xssx\":\"RA1\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"RA2\",\"dmms\":\"神木县人民法院\",\"fydz\":\"陕0821\",\"fyjb\":4,\"fyjc\":\"神\",\"gfdm\":3707,\"qybz\":1,\"sjdm\":\"RA0\",\"sm\":\"\",\"xssx\":\"RA2\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"RA3\",\"dmms\":\"府谷县人民法院\",\"fydz\":\"陕0822\",\"fyjb\":4,\"fyjc\":\"府\",\"gfdm\":3708,\"qybz\":1,\"sjdm\":\"RA0\",\"sm\":\"\",\"xssx\":\"RA3\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"RA4\",\"dmms\":\"横山县人民法院\",\"fydz\":\"陕0823\",\"fyjb\":4,\"fyjc\":\"横\",\"gfdm\":3709,\"qybz\":1,\"sjdm\":\"RA0\",\"sm\":\"\",\"xssx\":\"RA4\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"RA5\",\"dmms\":\"靖边县人民法院\",\"fydz\":\"陕0824\",\"fyjb\":4,\"fyjc\":\"靖\",\"gfdm\":3710,\"qybz\":1,\"sjdm\":\"RA0\",\"sm\":\"\",\"xssx\":\"RA5\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"RA6\",\"dmms\":\"定边县人民法院\",\"fydz\":\"陕0825\",\"fyjb\":4,\"fyjc\":\"定\",\"gfdm\":3711,\"qybz\":1,\"sjdm\":\"RA0\",\"sm\":\"\",\"xssx\":\"RA6\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"RA7\",\"dmms\":\"绥德县人民法院\",\"fydz\":\"陕0826\",\"fyjb\":4,\"fyjc\":\"绥\",\"gfdm\":3712,\"qybz\":1,\"sjdm\":\"RA0\",\"sm\":\"\",\"xssx\":\"RA7\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"RA8\",\"dmms\":\"米脂县人民法院\",\"fydz\":\"陕0827\",\"fyjb\":4,\"fyjc\":\"米\",\"gfdm\":3713,\"qybz\":1,\"sjdm\":\"RA0\",\"sm\":\"\",\"xssx\":\"RA8\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"RA9\",\"dmms\":\"佳县人民法院\",\"fydz\":\"陕0828\",\"fyjb\":4,\"fyjc\":\"佳\",\"gfdm\":3714,\"qybz\":1,\"sjdm\":\"RA0\",\"sm\":\"\",\"xssx\":\"RA9\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"RAA\",\"dmms\":\"吴堡县人民法院\",\"fydz\":\"陕0829\",\"fyjb\":4,\"fyjc\":\"吴堡\",\"gfdm\":3715,\"qybz\":1,\"sjdm\":\"RA0\",\"sm\":\"\",\"xssx\":\"RAA\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"RAB\",\"dmms\":\"清涧县人民法院\",\"fydz\":\"陕0830\",\"fyjb\":4,\"fyjc\":\"清\",\"gfdm\":3716,\"qybz\":1,\"sjdm\":\"RA0\",\"sm\":\"\",\"xssx\":\"RAB\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"RAC\",\"dmms\":\"子洲县人民法院\",\"fydz\":\"陕0831\",\"fyjb\":4,\"fyjc\":\"子洲\",\"gfdm\":3717,\"qybz\":1,\"sjdm\":\"RA0\",\"sm\":\"\",\"xssx\":\"RAC\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"RB0\",\"dmms\":\"西安铁路运输中级人民法院\",\"fydz\":\"陕71\",\"fyjb\":3,\"fyjc\":\"西铁中\",\"gfdm\":3718,\"qybz\":1,\"sjdm\":\"R00\",\"sm\":\"\",\"xssx\":\"RB0\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"RB1\",\"dmms\":\"安康铁路运输法院\",\"fydz\":\"陕7101\",\"fyjb\":4,\"fyjc\":\"安铁\",\"gfdm\":2233,\"qybz\":1,\"sjdm\":\"RB0\",\"sm\":\"\",\"xssx\":\"RB1\"},{\"area\":\"\",\"czbz\":\"GF2004-000-180\",\"dm\":\"RB2\",\"dmms\":\"西安铁路运输法院\",\"fydz\":\"陕7102\",\"fyjb\":4,\"fyjc\":\"西铁\",\"gfdm\":2234,\"qybz\":1,\"sjdm\":\"RB0\",\"sm\":\"\",\"xssx\":\"RB2\"}]"

    val dblbJson = "{\n" +
            "    \"rows\": [\n" +
            "      {\n" +
            "        \"bt\": \"并行节点测试1111111\",\n" +
            "        \"cjrq\": \"2018-08-31 10:07:03.0\",\n" +
            "        \"ngrDept\": \"立案庭\",\n" +
            "        \"ngrName\": \"OA系统管理员\",\n" +
            "        \"nodeName\": \"\",\n" +
            "        \"param\": {\n" +
            "          \"primaryId\": \"57d27d02-6b8c-47e0-a2d8-d0707a576b3b\",\n" +
            "          \"processInstancesId\": \"8ddca31a-acc2-11e8-b183-3a8427a833fb\",\n" +
            "          \"taskId\": \"199babae-acc4-11e8-b183-3a8427a833fb\",\n" +
            "          \"taskKey\": \"OA_FLOW_GWGL_NBFW_GD\"\n" +
            "        },\n" +
            "        \"wh\": \"\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"bt\": \"测试会签节点11111\",\n" +
            "        \"cjrq\": \"2018-08-31 09:28:45.0\",\n" +
            "        \"ngrDept\": \"立案庭\",\n" +
            "        \"ngrName\": \"OA系统管理员\",\n" +
            "        \"nodeName\": \"\",\n" +
            "        \"param\": {\n" +
            "          \"primaryId\": \"191ce5d2-41cd-4abe-91ff-319d6ed836a0\",\n" +
            "          \"processInstancesId\": \"33b50d65-acbd-11e8-b183-3a8427a833fb\",\n" +
            "          \"taskId\": \"0c580299-acc1-11e8-b183-3a8427a833fb\",\n" +
            "          \"taskKey\": \"OA_FLOW_GWGL_NBFW_GD\"\n" +
            "        },\n" +
            "        \"wh\": \"\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"bt\": \"流程测试1111\",\n" +
            "        \"cjrq\": \"2018-08-30 18:13:59.0\",\n" +
            "        \"ngrDept\": \"立案庭\",\n" +
            "        \"ngrName\": \"OA系统管理员\",\n" +
            "        \"nodeName\": \"\",\n" +
            "        \"param\": {\n" +
            "          \"primaryId\": \"8586c4e5-a2a3-4693-ba11-fcc0cd34edb7\",\n" +
            "          \"processInstancesId\": \"697c8224-ac3d-11e8-b183-3a8427a833fb\",\n" +
            "          \"taskId\": \"eca3fd82-ac3e-11e8-b183-3a8427a833fb\",\n" +
            "          \"taskKey\": \"OA_FLOW_GWGL_NBFW_NGRB\"\n" +
            "        },\n" +
            "        \"wh\": \"\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"bt\": \"sssssssssssssssssssssssssssssssssssssssss\",\n" +
            "        \"cjrq\": \"2018-07-05 09:35:08.0\",\n" +
            "        \"ngrDept\": \"立案庭\",\n" +
            "        \"ngrName\": \"OA系统管理员\",\n" +
            "        \"nodeName\": \"\",\n" +
            "        \"param\": {\n" +
            "          \"primaryId\": \"ec82ae1a-4980-4722-b868-5c8f229068f7\",\n" +
            "          \"processInstancesId\": \"a6fae168-7ff3-11e8-b52d-3a8427a833fb\",\n" +
            "          \"taskId\": \"034f7204-a03c-11e8-a829-3a8427a833fb\",\n" +
            "          \"taskKey\": \"OA_FLOW_GWGL_NBFW_HG\"\n" +
            "        },\n" +
            "        \"wh\": \"\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"bt\": \"31231\",\n" +
            "        \"cjrq\": \"2018-06-13 18:03:47.0\",\n" +
            "        \"ngrDept\": \"立案庭\",\n" +
            "        \"ngrName\": \"OA系统管理员\",\n" +
            "        \"nodeName\": \"\",\n" +
            "        \"param\": {\n" +
            "          \"primaryId\": \"6df4c821-0028-44ca-8a17-40dcc6936399\",\n" +
            "          \"processInstancesId\": \"1081dde0-6ef1-11e8-b04c-3010b3c972b4\",\n" +
            "          \"taskId\": \"e884df82-739a-11e8-acdb-3a8427a833fb\",\n" +
            "          \"taskKey\": \"OA_FLOW_GWGL_NBFW_NGRB\"\n" +
            "        },\n" +
            "        \"wh\": \"\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"total\": 6\n" +
            "  }\n"

    val loginJson = "{\"deptId\":\"069\",\"deptName\":\"立案庭\",\"fydm\":\"\",\"imei\":\"\",\"lastDate\":\"2018-09-09\",\"loginBusiType\":\"1\",\"loginName\":\"0000\",\"spUserName\":\"\",\"userId\":\"360000000\",\"userName\":\"OA系统管理员\",\"userTel\":\"\"}"

    val mainJson = "[{\"mlxx\":[{\"court\":\"11\",\"flowCode\":\"OA_FLOW_GWGL_NBFW\",\"moduleCode\":\"OA_FUN_GWGL\",\"spdCode\":\"OA_SPD_GWGL_NBFW\",\"text\":\"内部发文\"},{\"court\":\"11\",\"flowCode\":\"OA_FLOW_GWGL_WBFW\",\"moduleCode\":\"OA_FUN_GWGL\",\"spdCode\":\"OA_SPD_GWGL_WBFW\",\"text\":\"外部发文\"}],\"name\":\"公文管理\"},{\"mlxx\":[{\"court\":\"11\",\"flowCode\":\"OA_FLOW_XZZB_SBLY\",\"moduleCode\":\"OA_FUN_XZZB\",\"spdCode\":\"OA_SPD_XZZB_SBLY\",\"text\":\"设备领用\"},{\"court\":\"9\",\"flowCode\":\"OA_FLOW_XZZB_SBBF\",\"moduleCode\":\"OA_FUN_XZZB\",\"spdCode\":\"OA_SPD_XZZB_SBBF\",\"text\":\"设备报废\"},{\"court\":\"11\",\"flowCode\":\"OA_FLOW_XZZB_SBWX\",\"moduleCode\":\"OA_FUN_XZZB\",\"spdCode\":\"OA_SPD_XZZB_SBWX\",\"text\":\"设备维修\"}],\"name\":\"行政装备\"},{\"mlxx\":[{\"court\":\"5\",\"flowCode\":\"OA_FLOW_HQGL_YCSQ\",\"moduleCode\":\"OA_FUN_HQGL\",\"spdCode\":\"OA_SPD_HQGL_YCSQ\",\"text\":\"用车申请\"},{\"court\":\"4\",\"flowCode\":\"OA_FLOW_HQGL_WPLY\",\"moduleCode\":\"OA_FUN_HQGL\",\"spdCode\":\"OA_SPD_HQGL_WPLY\",\"text\":\"物品领用\"}],\"name\":\"后勤管理\"},{\"mlxx\":[{\"court\":\"3\",\"flowCode\":\"OA_FLOW_QJGL_GCGL\",\"moduleCode\":\"OA_FUN_QJGL\",\"spdCode\":\"OA_SPD_QJGL_GCSQ\",\"text\":\"公出申请\"},{\"court\":\"6\",\"flowCode\":\"OA_FLOW_QJGL_QJGL\",\"moduleCode\":\"OA_FUN_QJGL\",\"spdCode\":\"OA_SPD_QJGL_QJSQ\",\"text\":\"请假申请\"}],\"name\":\"请假管理\"},{\"mlxx\":[{\"court\":\"12\",\"flowCode\":\"OA_FLOW_JDGL_JDJLSQ\",\"moduleCode\":\"OA_FUN_JDGL\",\"spdCode\":\"OA_SPD_JDGL_JDJLSQ\",\"text\":\"接待申请\"}],\"name\":\"接待管理\"}]"

    val detailJson = "{\n" +
            "  \"spdData\": [\n" +
            "    {\n" +
            "      \"spdKey\": \"spdCode_qh\",\n" +
            "      \"spdValue\": \"OA_SPD_QJGL_QJSQ\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"spdKey\": \"fydm_hq\",\n" +
            "      \"spdValue\": \"R00\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"spdKey\": \"spdCode_qh\",\n" +
            "      \"spdValue\": \"OA_SPD_QJGL_QJSQ\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"spdKey\": \"spdCode_qh\",\n" +
            "      \"spdValue\": \"OA_SPD_QJGL_QJSQ\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"spdKey\": \"qjr_input\",\n" +
            "      \"spdValue\": \"OA系统管理员\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"spdKey\": \"fydm_hq\",\n" +
            "      \"spdValue\": \"R00\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"spdKey\": \"spdCode_qh\",\n" +
            "      \"spdValue\": \"OA_SPD_QJGL_QJSQ\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"spdKey\": \"bz_textarea\",\n" +
            "      \"spdValue\": \"\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"spdKey\": \"xjzljsy_select\",\n" +
            "      \"spdValue\": \"产假\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"spdKey\": \"qjsy_textarea\",\n" +
            "      \"spdValue\": \"332\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"spdKey\": \"szbm_input\",\n" +
            "      \"spdValue\": \"立案庭\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"spdKey\": \"zw_input\",\n" +
            "      \"spdValue\": \"\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"spdKey\": \"fydm_hq\",\n" +
            "      \"spdValue\": \"R00\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"spdKey\": \"fydm_hq\",\n" +
            "      \"spdValue\": \"R00\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"spdXx\": {\n" +
            "    \"assignName\": \"\",\n" +
            "    \"blsj\": \"\",\n" +
            "    \"bmbm\": \"3\",\n" +
            "    \"bmmc\": \"立案庭\",\n" +
            "    \"bt\": \"2018年06月29日_OA系统管理员_请假申请\",\n" +
            "    \"column1\": \"\",\n" +
            "    \"column10\": \"\",\n" +
            "    \"column2\": \"2018-06-29\",\n" +
            "    \"column3\": \"2018-06-12 00:00\",\n" +
            "    \"column4\": \"2018-06-13 00:00\",\n" +
            "    \"column5\": \"\",\n" +
            "    \"column6\": \"\",\n" +
            "    \"column7\": \"\",\n" +
            "    \"column8\": \"\",\n" +
            "    \"column9\": \"\",\n" +
            "    \"createUserId\": \"360000000\",\n" +
            "    \"createUserName\": \"OA系统管理员\",\n" +
            "    \"dbNodeId\": \"\",\n" +
            "    \"dbsprId\": \"\",\n" +
            "    \"dbsprmc\": \"\",\n" +
            "    \"dcllqtfyspdBfyCt\": 0,\n" +
            "    \"dcllqtfyspdCt\": 0,\n" +
            "    \"dcllqtfyspwcCt\": 0,\n" +
            "    \"dqh\": 0,\n" +
            "    \"dycount\": 0,\n" +
            "    \"fbrid\": \"\",\n" +
            "    \"fbrmc\": \"\",\n" +
            "    \"fbsj\": \"\",\n" +
            "    \"flowCode\": \"OA_FLOW_QJGL_QJGL\",\n" +
            "    \"flowName\": \"\",\n" +
            "    \"flowParams\": null,\n" +
            "    \"funCode\": \"OA_FUN_QJGL_QJSQ\",\n" +
            "    \"fydm\": \"R00\",\n" +
            "    \"fyjc\": \"陕\",\n" +
            "    \"fymc\": \"陕西省高级人民法院\",\n" +
            "    \"gdbz\": 0,\n" +
            "    \"gdlx\": \"\",\n" +
            "    \"gdrId\": \"\",\n" +
            "    \"gdrmc\": \"\",\n" +
            "    \"gdsj\": \"\",\n" +
            "    \"gdzt\": 0,\n" +
            "    \"hisNodeId\": \"\",\n" +
            "    \"hisNodeName\": \"\",\n" +
            "    \"hjjexx\": 0,\n" +
            "    \"htmlPath\": \"\",\n" +
            "    \"id\": \"6d15add2-eeb2-4883-99bd-ee91e8dd05a0\",\n" +
            "    \"imgPath\": \"\",\n" +
            "    \"isUpdateIndex\": \"\",\n" +
            "    \"isfb\": 0,\n" +
            "    \"jsrId\": \"\",\n" +
            "    \"jssj\": \"\",\n" +
            "    \"lcmc\": \"\",\n" +
            "    \"moduleCode\": \"OA_FUN_QJGL\",\n" +
            "    \"ndh\": \"\",\n" +
            "    \"nodeId\": \"OA_FLOW_QJGL_QJGL_SQR\",\n" +
            "    \"nodeName\": \"申请人\",\n" +
            "    \"processInstancesId\": \"fd454556-7b77-11e8-92ce-3a8427a833fb\",\n" +
            "    \"qhfs\": \"\",\n" +
            "    \"qybz\": 0,\n" +
            "    \"remoteTaskName\": \"\",\n" +
            "    \"sdwh\": \"\",\n" +
            "    \"sfbl\": \"\",\n" +
            "    \"sfdy\": 0,\n" +
            "    \"sfth\": \"0\",\n" +
            "    \"sfyxgq\": 0,\n" +
            "    \"sfzdgd\": 0,\n" +
            "    \"sjrdz\": \"\",\n" +
            "    \"sjrlxfs\": \"\",\n" +
            "    \"sjrmc\": \"\",\n" +
            "    \"sort\": \"\",\n" +
            "    \"spdCode\": \"OA_SPD_QJGL_QJSQ\",\n" +
            "    \"spdJson\": \"\",\n" +
            "    \"spdNodeName\": \"\",\n" +
            "    \"spdSprmc\": \"\",\n" +
            "    \"spdTaskNodeHis\": \"启动#申请人#部门领导审批\",\n" +
            "    \"spdVersion\": 1,\n" +
            "    \"spdid\": \"\",\n" +
            "    \"sprid\": \"360000000\",\n" +
            "    \"sprmc\": \"OA系统管理员\",\n" +
            "    \"startFlow\": \"\",\n" +
            "    \"status\": \"2\",\n" +
            "    \"sysTime\": \"2018-06-29 16:39:51.0\",\n" +
            "    \"taskId\": \"\",\n" +
            "    \"updateTime\": \"\",\n" +
            "    \"url\": \"\",\n" +
            "    \"wh\": \"\",\n" +
            "    \"whid\": \"\",\n" +
            "    \"wjcjfs\": 0,\n" +
            "    \"xtlx\": \"\",\n" +
            "    \"ycid\": \"\",\n" +
            "    \"ycrs_input\": \"\",\n" +
            "    \"ycspdid\": \"\",\n" +
            "    \"ytbm_input\": \"\",\n" +
            "    \"ytmc_input\": \"\",\n" +
            "    \"ytspdid\": \"\",\n" +
            "    \"zfbz\": 0,\n" +
            "    \"zhmc\": \"\"\n" +
            "  }\n" +
            "}"


    fun getCourtMaybe(): Maybe<List<Court>> {
        return Maybe.create<List<Court>> {
            val list = CourtUseCase().toResult(courtJson)
            it.onSuccess(list)
        }
    }

    fun getLoginMaybe(): Maybe<Unit> {
        return Maybe.just(Unit)
    }

//    fun getDblbMaybe(): Maybe<Page<Dbxx>> {
//        val mainItem = MainItem(text = "内部发文", resId = R.mipmap.bangong_01, moduleCode = "OA_FUN_GWGL", flowCode = "OA_FLOW_GWGL_NBFW", spdCode = "OA_SPD_GWGL_NBFW")
//        return Maybe.just(DbxxUseCase(mainItem = mainItem, pageNo = 0).toResult(dblbJson))
//    }

    fun getDetail(): Maybe<Detail> {
        val detail = ComponentHolder.appComponent.getGson().fromJson(detailJson, Detail::class.java)
        return Maybe.just(detail)
    }

}