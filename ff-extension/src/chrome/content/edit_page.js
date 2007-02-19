/* oncommand event */
function edit_page()
  {

    var src = getWebNavigation().currentURI.spec;

    // window.alert("hello! 2 " + src);

    var file = Components.classes["@mozilla.org/file/local;1"].createInstance(Components.interfaces.nsILocalFile);

    file.initWithPath("/Applications/SeaMonkey.app/Contents/MacOS/seamonkey");      

    var process = Components.classes["@mozilla.org/process/util;1"].createInstance(Components.interfaces.nsIProcess);
    process.init(file);

    var args = new Array("-editor", src);

    process.run(false, args, args.length);

  }
