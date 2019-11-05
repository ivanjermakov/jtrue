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
    : andGroup
    | orGroup
    ;

andGroup
    : OP validationRules CP
    | AND_OP OP validationRules CP
    ;

orGroup
    : OR_OP OP validationRules CP
    ;

//TODO: support empty groups
validationRules
    : validationRule (COMMA validationRule)* COMMA?
    ;

validationRule
    : predicate errorMessage?
    | group
    | fieldRule
    ;

errorMessage
    : ARROW str
    ;

fieldRule
    : fieldSelector object
    ;

fieldSelector
    : singleFieldSelector
    | allFieldSelector
    | anyFieldSelector
    ;

singleFieldSelector
    : fieldPath
    ;

allFieldSelector
    : ALL_OP OP fieldPaths CP
    ;

anyFieldSelector
    : ANY_OP OP fieldPaths CP
    ;

fieldPaths
    : fieldPath (COMMA fieldPath)*
    ;

fieldPath
    : DOT
    | (DOT WORD)+
    ;

//    named predicates only for now
predicate
    : predicateName
    | predicateName OP CP
    | predicateName OP predicateParameters CP
    ;

predicateName
    : WORD
    ;

predicateParameters
    : predicateParameter (COMMA predicateParameter)* COMMA?
    ;

predicateParameter
    : num
    | str
    | bool
    ;

num
    : NUMBER
    ;

str
    : QUOTE .*? QUOTE
    ;

bool
    : (TRUE | FALSE)
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

QUOTE
    : '\''
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

ARROW
    : '->'
    ;

TRUE
    : 'true'
    ;

FALSE
    : 'false'
    ;

NUMBER
    : [-+]? [0-9]+ (DOT [0-9]+)?
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