# plum-api
Java API for Plum Lightpad

Usage:
PlumContext ctx = new PlumContext("email", "password");
List<House> houses = HouseService.getHouses(ctx);

String sid = "xxx-xxxxx";
HouseService.triggerScene(ctx, sid);


Base work was done by mikenemat at https://github.com/mikenemat/plum-probe
It took me a lot of work to reverse engineer this. Please share credit if you reuse the code.