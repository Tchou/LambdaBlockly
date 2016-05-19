
Blockly.Blocks['lambda'] = {
  init: function() {
    this.appendStatementInput("NAME")
        .setCheck(null)
        .appendField("λ")
        .appendField(new Blockly.FieldTextInput("x"), "PARAM")
        .appendField("•");
    this.setPreviousStatement(true, null);
    this.setColour(120);
    this.setTooltip('');
    this.setHelpUrl('http://www.example.com/');
  }
};


Blockly.Blocks['var'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldTextInput("x"), "NAME");
    this.setPreviousStatement(true, null);
    this.setColour(330);
    this.setTooltip('');
    this.setHelpUrl('http://www.example.com/');
  }
};


Blockly.Blocks['apply'] = {
  init: function() {
    this.appendStatementInput("FUN")
        .setCheck(null);
    this.appendDummyInput();
    this.appendStatementInput("ARG")
        .setCheck(null);
    this.setPreviousStatement(true, null);
    this.setColour(225);
    this.setTooltip('');
    this.setHelpUrl('http://www.example.com/');
  }
};