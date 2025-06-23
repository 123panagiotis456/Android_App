@echo off
REM Scaffold for SupermarketManager project on Windows CMD

REM Root = current directory
set ROOT=%~dp0

REM Delete if exists (προαιρετικό)
REM rmdir /s /q "%ROOT%app"

REM Δημιουργία φακέλων
md "%ROOT%app\src\main\java\com\example\supermarketmanager\data\entities"
md "%ROOT%app\src\main\java\com\example\supermarketmanager\data\dao"
md "%ROOT%app\src\main\java\com\example\supermarketmanager\ui\viewmodel"
md "%ROOT%app\src\main\java\com\example\supermarketmanager\ui\adapter"
md "%ROOT%app\src\main\java\com\example\supermarketmanager\ui\fragment"
md "%ROOT%app\src\main\res\layout"
md "%ROOT%app\src\main\res\menu"
md "%ROOT%app\src\main\res\navigation"
md "%ROOT%app\src\main\res\values"

md "%ROOT%gradle\wrapper"
md "%ROOT%app"

echo Folder structure created successfully!
pause
