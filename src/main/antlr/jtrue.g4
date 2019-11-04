grammar jtrue;

@header {
    package com.github.ivanjermakov.jtrue.lang;
}

object
    : group EOF?
    | negate EOF?
    ;

negate
    : '!' group
    | '!(' group ')'
    ;

group
    : and_group
    | or_group
    ;

and_group
    : '&(' validation_rules ')'
    ;

or_group
    : '|(' validation_rules ')'
    ;

validation_rules
    : validation_rule (',' validation_rule)* ','?
    ;

validation_rule
    : predicate
    | group
    | field_selector object
    ;

field_selector
    : field_path
    | '*(' field_path (',' field_path)* ')'
    | '?(' field_path (',' field_path)* ')'
    ;

field_path
    : '.'
    | '.' WORD
    | ('.' WORD)+
    ;

//    named predicates only for now
predicate
    : WORD
    ;

WORD
    : ([a-zA-Z0-9_$])+
    ;

WS
   : [ \n] + -> skip
   ;

NL
    : '\r'? '\n' -> skip
    ;
