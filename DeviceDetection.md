# Introduction #

We need to determine an initial cut at what we look at in device detection.


# Details #

I suggest we use the relevant attributes of the Build object and the Build.VERSION object.

The patterns to match against would generally be string matches, except in the case of INT\_SDK, which I'd suggest could be matched with any of the following style of patterns:

**10** <10
**<=10** >=10
**=10**

We could do boolean combination as an enhancement, but these together with multiple rules are sufficient.