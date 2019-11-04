grammar Jtrue;

@header {
    package com.github.ivanjermakov.antlr;
}

object
    : group EOF?
    | negate EOF?
    ;

negate
    : NOT_OP group
    ;

group
    : and_group
    | or_group
    ;

and_group
    : AND_OP OP validation_rules CP
    ;

or_group
    : OR_OP OP validation_rules CP
    ;

validation_rules
    : validation_rule (COMMA validation_rule)* COMMA?
    ;

validation_rule
    : predicate
    | group
    | field_rule
    ;

field_rule
    : field_selector object
    ;

field_selector
    : single_field_selector
    | all_selector
    | any_selector
    ;

single_field_selector
    : field_path
    ;

all_selector
    : ALL_OP OP field_path (COMMA field_path)* CP
    ;

any_selector
    : ANY_OP OP field_path (COMMA field_path)* CP
    ;

field_path
    : DOT
    | DOT WORD
    | (DOT WORD)+
    ;

//    named predicates only for now
predicate
    : WORD
    ;

COMMENT
    :  '#' ~[\n]* '\n' -> skip
    ;

DOT
    : '.'
    ;

COMMA
    : ','
    ;

OP
    : '('
    ;

CP
    : ')'
    ;

NOT_OP
    : '!'
    ;

AND_OP
    : '&'
    ;

OR_OP
    : '|'
    ;

ALL_OP
    : '*'
    ;


ANY_OP
    : '?'
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