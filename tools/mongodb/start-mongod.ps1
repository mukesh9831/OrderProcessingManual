# Start mongod using the local installation created by install-mongodb.ps1
param(
    [string]$MongoBinPath = "$(Join-Path $env:USERPROFILE 'Tools\\mongodb' )",
    [string]$DataDir = "$(Join-Path $env:USERPROFILE 'mongodb-data')"
)

# Attempt to find the bin folder
$bin = Get-ChildItem -Path $MongoBinPath -Directory -ErrorAction SilentlyContinue | Where-Object { Test-Path (Join-Path $_.FullName 'bin\\mongod.exe') } | Select-Object -First 1
if ($null -eq $bin) {
    # Maybe user passed full bin path
    if (Test-Path (Join-Path $MongoBinPath 'mongod.exe')) {
        $mongod = Join-Path $MongoBinPath 'mongod.exe'
    } else {
        Write-Error "Could not locate mongod.exe. Ensure MongoDB is installed under $MongoBinPath or pass the bin path explicitly."
        exit 1
    }
} else {
    $mongod = Join-Path $bin.FullName 'bin\\mongod.exe'
}

if (-Not (Test-Path $DataDir)) {
    New-Item -ItemType Directory -Path $DataDir | Out-Null
}

Write-Host "Starting mongod from: $mongod"
Start-Process -FilePath $mongod -ArgumentList "--dbpath", "$DataDir" -NoNewWindow
Write-Host "mongod started (detached). Check logs in the console that started mongod or in the data dir for diagnostic files."
