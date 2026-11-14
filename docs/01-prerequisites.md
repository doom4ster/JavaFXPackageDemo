# Prerequisites for Packaging JavaFX Apps
So, whilst the in-built JavaFX Plugin for InteliJ is a great starting point,
it undoubtably has flaws. This is due to a combination unconsidered edge-cases the plugin
developers and the fact JavaFX support is generally not a high priority.

One of the things not considered is warning / information on OS-specific prerequisites for 
packaging your application. 

## Linux
Linux is probably both the most "straight forward" and "temperamental" of the operating systems to
package on. _(Is anyone actually surprised by this?)_

It is straight forward as, other than the specific JDK you wish to use, the packaging itself is pretty
much plug and play. However, I give no guarantees on individual distros and desktop environments. I have 
only built on CachyOS (Arch-Based) so far. Hence, for distros may have various quirks such as different default
native libraries, issues that are dependent on desktop environment, and other such headaches. 

_(WIP: In the future, it would be the intention for potential contributors to populate this section with information regarding
specific, per-distro, requirements)_ 

## Windows
Windows requires several things to be considered but, I must confess, I do not have the experience myself to give details.
I am aware, in passing, that something known as the WiX Toolset is required to create `.exe` files. Furthermore, I have 
vague memories of, when doing literally anything on Windows to do with development, having to configure the PATH to 
actually get the PC to recognise where all the dependencies are. 

_(WIP: If anyone is more versed in the details / headaches of building on Windows, I would be very much grateful for your
contribution to the documentation!)_

## macOS
The first requirement for packaging on macOS is... don't... use a real PC...

Jokes aside, in the 31 years I have been alive on this planet, I have never once touched a computer that is running macOS.
As such, I have genuinely no idea what walled-garden level headaches are awaiting any poor soul trying to package apps on
that OS. 

_(WIP: This one would require a much more in-depth contribution from someone, as not only would I need someone to write
the guidence, but then I would have to go away and test if it is actually correct, which would mean having to use macOS 
for the first time in my life. So, if anyone has the patience to help me with this one, please first create an issue so 
we can discuss how we can approach this guidance in an effective manner)_

