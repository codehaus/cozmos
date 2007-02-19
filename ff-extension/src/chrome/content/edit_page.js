/* oncommand event */
function edit_page()
  {
    // set the source 
    var src = window.location.href;
    // set the destination filename and open save dialog

    window.alert("hello!" + src)

    var file = Components.classes["@mozilla.org/file/local;1"].createInstance(Components.interfaces.nsILocalFile);

    /*FIXME isWritable()
    // Check the file is writable
    file.initWithPath(dest);
    if (!file.isWritable())
      {
        alert(gStringBundle.GetStringFromName("mozvertErrorDest"));
        return;
      }*/

    // Set the path to convert and check it
    file.initWithPath("/Applications/SeaMonkey.app/Contents/MacOS/seamonkey");      

    var process = Components.classes["@mozilla.org/process/util;1"].createInstance(Components.interfaces.nsIProcess);
    process.init(file);

    var args = new Array("-editor", src);

    process.run(false, args, args.length);


  }
