/* oncommand event */
function edit_page() {

    var url = getWebNavigation().currentURI.spec;

    if (url.substr(0,4) == "http") {
        if (url.indexOf("?") > 0) {
            url = url + "&editMode=true";
        } else {
            url = url + "?editMode=true";
        }
    }

    // window.alert("url= " + url);

    var file = Components.classes["@mozilla.org/file/local;1"].createInstance(Components.interfaces.nsILocalFile);

    // Mac OS X
    file.initWithPath("/Applications/Macromedia Dreamweaver 8/Dreamweaver 8/Contents/MacOS/Dreamweaver");
    var args = new Array(url);
    if (!file.exists()) {
        file.initWithPath("/Applications/SeaMonkey.app/Contents/MacOS/seamonkey");
        args = new Array("-editor", url);
    }

    // Windows
    if (!file.exists()) {
        file.initWithPath("C:/Program Files/Macromedia/Dreamweaver 8/dreamweaver.exe");
        args = new Array(url);
    }
    if (!file.exists()) {
        file.initWithPath("C:/Program Files/mozilla.org/SeaMonkey/seamonkey.exe");
        args = new Array("-editor", url);
    }

    var process = Components.classes["@mozilla.org/process/util;1"].createInstance(Components.interfaces.nsIProcess);
    process.init(file);

    process.run(false, args, args.length);

  }
