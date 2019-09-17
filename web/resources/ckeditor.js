/**
 * Copyright (c) 2003-2014, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
         docType: '<!DOCTYPE HTML>',
	 config.language = 'es';
         config.enterMode = CKEDITOR.ENTER_BR;
         config.coreStyles_bold = { element: 'b', overrides: 'strong' };         
         config.coreStyles_italic = { element: 'i', overrides: 'em' };
         config.disableNativeSpellChecker = false;
         config.forcePasteAsPlainText = true;         
         config.pasteFromWordRemoveFontStyles = true;         
         config.pasteFromWordRemoveStyles = true;         
         config.removePlugins = 'liststyle,tabletools,scayt,menubutton,contextmenu';
         config.fillEmptyBlocks = false;
         config.entities = false;
         config.basicEntities = false;         
	// config.uiColor = '#AADC6E';
};

