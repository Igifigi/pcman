@echo off
setlocal enabledelayedexpansion
set "SRC_DIR=src"
set "OUT_DIR=tmp"

if not exist "%OUT_DIR%" mkdir "%OUT_DIR%"


dir /s /b "%SRC_DIR%\*.java" > "%OUT_DIR%\sources.txt"

javac -d "%OUT_DIR%" @"%OUT_DIR%\sources.txt"

del "%OUT_DIR%\sources.txt"

if exist "%SRC_DIR%\resources\" (
    xcopy "%SRC_DIR%\resources\*" "%OUT_DIR%\" /E /Y >nul 2>&1
)

java.exe -cp "%OUT_DIR%" game.Main
