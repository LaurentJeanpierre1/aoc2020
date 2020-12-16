grammar bags;

start : fichier EOF;

WS: [ ,] -> skip;

BAGS: 'bag''s'?;
CONTAIN: 'contain';
NO_OTHER_BAGS : 'no other bags' ;
INT : [0-9]+;
COLOR: [a-z][a-z]*;

fichier : ligne*;

ligne: bag_name CONTAIN content '.' '\n'? { System.out.printf("%s -> %s\n", $bag_name.name, $content.res.toString()); };

content returns [List<Content> res]: NO_OTHER_BAGS { $res = new ArrayList<Content>(); }
       | l+=bag + { $res = ($l).stream().map(b->b.aBag).collect(Collectors.toList()); }
       ;

bag_name returns [String name] : l+=COLOR + BAGS { $name = $l.stream().map(c->c.getText()).collect(Collectors.joining()); };

bag returns [Content aBag]: INT bag_name { $aBag = new Content($bag_name.name, $INT.int); };


