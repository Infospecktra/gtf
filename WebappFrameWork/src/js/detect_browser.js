//** globle vars
var isNew = 0; //** IE6 or NS6
var isNS4 = 0;
var isIE4 = 0;

var fullBrowserName = (navigator.appName + parseInt(navigator.appVersion));

if (parseInt(navigator.appVersion)>=5)	isNew = 1;
else if (fullBrowserName=="Netscape4") isNS4 = 1;
else if (fullBrowserName=="Microsoft Internet Explorer4") isIE4 = 1;
