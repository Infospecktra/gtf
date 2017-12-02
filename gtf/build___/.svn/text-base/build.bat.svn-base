ECHO OFF
ECHO ***************************************************************
ECHO * Copyright:   Copyright (c) 2000                             *
ECHO * @author:     Steven Yang                                    *
ECHO * Company:     Screaming Media                                *
ECHO * Description: to compile the SITEWare in command line mode   *
ECHO *               created on Nov 1, 2000                        *
ECHO ***************************************************************

set ANT_HOME=c:\apache-ant-1.6.5
rem set JAVA_HOME=c:\j2sdk1.4.2_09

rem set JAVA_HOME=C:\Program Files\Java\jdk1.6.0_33
set JAVA_HOME=C:\Program Files\Java\jdk1.6.0_20

set PATH=%ANT_HOME%\bin
set CLASSPATH=%ANT_HOME%\lib\ant.jar;%ANT_HOME%\lib\jaxp.jar;%ANT_HOME%\lib\parser.jar

Echo %PATH%

call ant -buildfile build.xml

pause