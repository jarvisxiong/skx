pushd ..
robocopy ..\src-code\ykx-common\src\com ykx-common\src\main\java\com /MIR /DCOPY:T /R:3 /LOG+:migrate.log
robocopy ..\src-code\ykx-entity\src\com ykx-entity\src\main\java\com /MIR /DCOPY:T /R:3 /LOG+:migrate.log
robocopy ..\src-code\netsharp\src\org ykx-netsharp\src\main\java\org /MIR /DCOPY:T /R:3 /LOG+:migrate.log
robocopy ..\src-code\ykx-service\src\com ykx-service\src\main\java\com /MIR /DCOPY:T /R:3 /LOG+:migrate.log
robocopy ..\src-code\ykx-test\src\com ykx-test\src\test\java\com /MIR /DCOPY:T /R:3 /LOG+:migrate.log
robocopy ..\src-code\ykx-visi\src\com ykx-visi\src\main\java\com /MIR /DCOPY:T /R:3 /LOG+:migrate.log
robocopy ..\src-code\ykx-visi-web\src\com ykx-visi-web\src\main\java\com /MIR /DCOPY:T /R:3 /LOG+:migrate.log
robocopy ..\src-code\ykx-web\src\com ykx-www\src\main\java\com /MIR /DCOPY:T /R:3 /LOG+:migrate.log



robocopy ..\src-code\ykx-common\test\com ykx-common\src\test\java\com /MIR /DCOPY:T /R:3 /LOG+:migrate.log
robocopy ..\src-code\ykx-entity\test\com ykx-entity\src\test\java\com /MIR /DCOPY:T /R:3 /LOG+:migrate.log
robocopy ..\src-code\netsharp\test\org ykx-netsharp\src\test\java\org /MIR /DCOPY:T /R:3 /LOG+:migrate.log
robocopy ..\src-code\ykx-service\test\com ykx-service\src\test\java\com /MIR /DCOPY:T /R:3 /LOG+:migrate.log
robocopy ..\src-code\ykx-test\test\com ykx-test\src\test\java\com /MIR /DCOPY:T /R:3 /LOG+:migrate.log
robocopy ..\src-code\ykx-visi-web\test\com ykx-visi-web\src\test\java\com /MIR /DCOPY:T /R:3 /LOG+:migrate.log
robocopy ..\src-code\ykx-web\test\com ykx-www\src\test\java\com /MIR /DCOPY:T /R:3 /LOG+:migrate.log

robocopy ..\src-code\ykx-visi-web\WebContent ykx-visi-web\src\main\webapp  /MIR /DCOPY:T /R:3 /LOG+:migrate.log  /XF *.jar /XD classes
robocopy ..\src-code\ykx-web\WebContent ykx-www\src\main\webapp /MIR /DCOPY:T /R:3 /LOG+:migrate.log /XF *.jar /XD classes

popd