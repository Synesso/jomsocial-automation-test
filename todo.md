Capture screenshots on error.

        } catch(ElementNotVisibleException e) {
            try {
                ScreenshotException e2 = (ScreenshotException) e.getCause();
                byte[] btDataFile = new sun.misc.BASE64Decoder().decodeBuffer(e2.getBase64EncodedScreenshot());
                File of = new File("error.png");
                FileOutputStream osf = new FileOutputStream(of);
                osf.write(btDataFile);
                osf.flush();
            } catch (IOException e1) {
                throw new RuntimeException(e1);
            }
            throw e;

Why does gradle take such a long time to test? Why does it run the same test over and over?

try having separate instances for different users. Does that speed things up? Does it make for better tests?

From Hung:

1. Do you have any kind of test script loader? like we have 200 test scripts and we want to run all of them consiquently
or we might want to re-run about 20 or 50 of them or just run failed test scripts for double check. Futher more, can we
build an self-execution file?

2. Can you show the USER_A and USER_B settings or how we set up configuration options for test script like: target url,
admin password, target browser ...

3. On the test script we will look up some html element by name or id, I'm not sure it is harded code on your script or
 not but it might be changed from JomSocial version A to version B, so element name or id should not be harded code, it
  should be read from configuration option similiar as #2

4. Can you show failed scenario case? how will the script deal with failed case? Is it possible to automatically re-run
failed test script for double check?

5. Can you show an example on how to reuse your test script on writing another test script to verify that like action
will generate global notification?
