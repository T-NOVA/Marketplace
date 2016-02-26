REM Copy slacore project to $1 directory parameter
REM The slacore project is located in %ORIGIN%. Change the variable 
REM according to your location

SET SYNC_COMMAND="c:\Program Files (x86)\BlinkSync"\blinksync.exe
SET ORIGIN=c:/AtosSLA/sourceCode/trunk
SET DESTINATION=%1
SET CURRENTDIR=%CD%

IF not exist "%DESTINATION%" (mkdir "%DESTINATION%")
CD %ORIGIN%

copy configuration.properties.sample %DESTINATION%
copy pom.xml %DESTINATION%
copy README.md %DESTINATION%

%SYNC_COMMAND% -d "%ORIGIN%\docs" "%DESTINATION%\docs"
%SYNC_COMMAND% -d "%ORIGIN%\samples" "%DESTINATION%\samples"
%SYNC_COMMAND% -d "%ORIGIN%\bin" "%DESTINATION%\bin"

%SYNC_COMMAND% -d "%ORIGIN%\sla-enforcement\src" "%DESTINATION%\sla-enforcement\src"
copy "%ORIGIN%\sla-enforcement\pom.xml" "%DESTINATION%\sla-enforcement\" 

IF not exist "%DESTINATION%\sla-personalization\src" (mkdir "%DESTINATION%\sla-personalization\src")
IF not exist "%DESTINATION%\sla-personalization\pom.xml" (copy "%ORIGIN%\sla-personalization\pom.xml" "%DESTINATION%\sla-personalization\")
IF not exist "%DESTINATION%\sla-personalization\src\main\resources" (mkdir "%DESTINATION%\sla-personalization\src\main\resources\")
IF not exist "%DESTINATION%\sla-personalization\src\main\resources\security-context.xml" (copy "%ORIGIN%\sla-personalization\src\main\resources\security-context.xml" "%DESTINATION%\sla-personalization\src\main\resources\")

%SYNC_COMMAND% -d "%ORIGIN%\sla-repository\src" "%DESTINATION%\sla-repository\src"
copy "%ORIGIN%\sla-repository\pom.xml" "%DESTINATION%\sla-repository\" 

%SYNC_COMMAND% -d "%ORIGIN%\sla-tools\src" "%DESTINATION%\sla-tools\src"
copy "%ORIGIN%\sla-tools\pom.xml" "%DESTINATION%\sla-tools\" 

%SYNC_COMMAND% -d "%ORIGIN%\sla-service\src" "%DESTINATION%\sla-service\src"
copy "%ORIGIN%\sla-service\pom.xml" "%DESTINATION%\sla-service\" 

CD %CURRENTDIR%