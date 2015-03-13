<#import "spring.ftl" as spring />

<meta name="pragma"        content="no-cache"/>
<meta name="cache-control" content="no-cache"/>
<meta name="expires"       content="0"/>
<meta charset="utf-8"/>

<script type="text/javascript" src="<@spring.url '/resources/script/toggleCheckboxes.js'/>"></script>

<link rel="stylesheet" href="<@spring.url '/resources/css/pls-default.css'/>"/>

<#assign css>
    <@spring.theme "css"/>
</#assign>

<#if (css?length > 0)>
    <link rel="stylesheet" type="text/css" href="<@spring.url '${css}/style.css'/>"/>
</#if>


