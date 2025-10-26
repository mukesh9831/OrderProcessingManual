# Download and extract MongoDB Community Server (Windows portable zip)
# Usage: Run from PowerShell: .\install-mongodb.ps1

param(
    [string]$Version = "6.0.14",
    [string]$Arch = "x86_64",
    [string]$InstallDir = "$env:USERPROFILE\\Tools\\mongodb"
)

$ErrorActionPreference = 'Stop'

Write-Host "Installing MongoDB $Version ($Arch) to $InstallDir"

$baseUrl = "https://fastdl.mongodb.org/windows"
$zipName = "mongodb-windows-$Arch-$Version.zip"
$downloadUrl = "$baseUrl/$zipName"

$destZip = Join-Path $env:TEMP $zipName

if (-Not (Test-Path $InstallDir)) {
    New-Item -ItemType Directory -Path $InstallDir -Force | Out-Null
}

Write-Host "Downloading $downloadUrl..."
Invoke-WebRequest -Uri $downloadUrl -OutFile $destZip -UseBasicParsing

Write-Host "Extracting to $InstallDir..."
Add-Type -AssemblyName System.IO.Compression.FileSystem
[System.IO.Compression.ZipFile]::ExtractToDirectory($destZip, $InstallDir)

# The zip contains a folder like "mongodb-windows-x86_64-6.0.14"
$extracted = Get-ChildItem -Directory $InstallDir | Where-Object { $_.Name -like "mongodb-windows*" } | Select-Object -First 1
if ($null -eq $extracted) {
    Write-Error "Extraction failed or unexpected archive layout."
    exit 1
}

$binPath = Join-Path $extracted.FullName "bin"
Write-Host "MongoDB binaries are in: $binPath"

# Optionally add to PATH for current session
$env:PATH = "$binPath;$env:PATH"
Write-Host "Added MongoDB bin to PATH for this session. To persist, add $binPath to your user PATH."

# Create default data directory
$dataDir = Join-Path $env:USERPROFILE "mongodb-data"
if (-Not (Test-Path $dataDir)) {
    New-Item -ItemType Directory -Path $dataDir | Out-Null
}

Write-Host "Created data directory: $dataDir"

Write-Host "Installation complete. Run 'Start-Process -NoNewWindow -FilePath (Join-Path '$binPath' 'mongod.exe') -ArgumentList '--dbpath','$dataDir' to start mongod."
