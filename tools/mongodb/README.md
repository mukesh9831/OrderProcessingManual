Local MongoDB install helper

This folder contains helper PowerShell scripts to download and run MongoDB Community Server without admin rights.

Steps:

1. Run the installer script (powershell may block script execution; run in an elevated prompt or set execution policy for the session):

```powershell
# from repository root
cd tools\mongodb
powershell -ExecutionPolicy Bypass -File .\install-mongodb.ps1
```

2. Start mongod (detached):

```powershell
powershell -ExecutionPolicy Bypass -File .\start-mongod.ps1
```

3. Connect with the mongo shell or a GUI (Compass). The installer extracts the mongosh and mongo tools to the install folder's `bin` directory. You can run `mongosh` or `mongo` from there or add it to your PATH.

Notes:
- Default install version in the script is 6.0.14. Edit `install-mongodb.ps1` to change the version.
- The script downloads from mongodb.org; ensure you have network access and enough disk space.
- To persist bin to PATH for future sessions, add the extracted `bin` folder under `%USERPROFILE%\\Tools\\mongodb\\<extracted-folder>\\bin` to your User PATH in Windows settings.
