/*
 * Written By Charles M. Chen 
 * 
 * Created on Jan 1, 2006
 *
 */

package org.cmc.music.clean;

import java.util.HashMap;
import java.util.Map;

import org.cmc.music.metadata.MusicMetadataConstants;

public abstract class Diacriticals implements MusicMetadataConstants
{

	private static final Map DIACRITICALS = new HashMap();
	static
	{
/*		DIACRITICALS.put("À", "À");
		DIACRITICALS.put("Á", "Á");
		DIACRITICALS.put("Â", "Â");
		DIACRITICALS.put("Ã", "Ã");
		DIACRITICALS.put("Ä", "Ä");
		DIACRITICALS.put("Å", "a");
		DIACRITICALS.put("Æ", "ae");
		DIACRITICALS.put("Ç", "Ç");
		DIACRITICALS.put("È", "È");
		DIACRITICALS.put("É", "É");
		DIACRITICALS.put("Ê", "Ê");
		DIACRITICALS.put("Ë", "Ë");
		DIACRITICALS.put("Ì", "Ì");
		DIACRITICALS.put("Í", "Í");
		DIACRITICALS.put("Î", "Î");
		DIACRITICALS.put("Ï", "Ï");
		DIACRITICALS.put("Ñ", "Ñ");
		DIACRITICALS.put("Ò", "Ò");
		DIACRITICALS.put("Ó", "Ó");
		DIACRITICALS.put("Ô", "Ô");
		DIACRITICALS.put("Õ", "Õ");
		DIACRITICALS.put("Ö", "Ö");
		DIACRITICALS.put("Ø", "o");
		DIACRITICALS.put("Ù", "Ù");
		DIACRITICALS.put("Ú", "Ú");
		DIACRITICALS.put("Û", "Û");
		DIACRITICALS.put("Ü", "Ü");
		DIACRITICALS.put("Ý", "Ý");
		DIACRITICALS.put("à", "à");
		DIACRITICALS.put("á", "á");
		DIACRITICALS.put("â", "â");
		DIACRITICALS.put("ã", "ã");
		DIACRITICALS.put("â", "â");
		DIACRITICALS.put("ä", "ä");
		DIACRITICALS.put("å", "a");
		DIACRITICALS.put("æ", "ae");
		DIACRITICALS.put("ç", "ç");
		DIACRITICALS.put("è", "è");
		DIACRITICALS.put("é", "é");
		DIACRITICALS.put("ê", "ê");
		DIACRITICALS.put("ë", "ë");
		DIACRITICALS.put("ì", "ì");
		DIACRITICALS.put("í", "í");
		DIACRITICALS.put("î", "î");
		DIACRITICALS.put("ï", "ï");
		DIACRITICALS.put("ð", "o");
		DIACRITICALS.put("ñ", "n");
		DIACRITICALS.put("ò", "o");
		DIACRITICALS.put("ó", "o");
		DIACRITICALS.put("ô", "o");
		DIACRITICALS.put("õ", "o");
		DIACRITICALS.put("ö", "o");
		DIACRITICALS.put("ø", "o");
		DIACRITICALS.put("ù", "u");
		DIACRITICALS.put("ú", "u");
		DIACRITICALS.put("û", "u");
		DIACRITICALS.put("ü", "u");
		DIACRITICALS.put("ý", "u");*/
	}

	public static final String convertDiacriticals(String s)
	{
		StringBuffer result = new StringBuffer();

		char chars[] = s.toCharArray();
		for (int i = 0; i < chars.length; i++)
		{
			char c = chars[i];
			String replacement = (String) DIACRITICALS.get("" + c);
			if (null != replacement)
				result.append(replacement);
			else
				result.append(c);
		}

		return result.toString();
	}

}
