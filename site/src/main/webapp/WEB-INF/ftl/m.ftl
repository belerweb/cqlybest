<?xml version="1.0" encoding="UTF-8"?>
<urlset>
	<url>
		<loc>${Options.site_url!}</loc>
		<lastmod>${now?string('yyyy-MM-dd')}</lastmod>
		<changefreq>always</changefreq>
		<priority>1.0</priority>
		<data>
			<display>
				<pc_url_pattern>${Options.site_url!}/</pc_url_pattern>
				<html5_url_pattern>${Options.site_mobile_url!}/</html5_url_pattern>
			</display>
		</data>
	</url>
	<url>
		<loc>${Options.site_url!}</loc>
		<lastmod>${now?string('yyyy-MM-dd')}</lastmod>
		<changefreq>always</changefreq>
		<priority>1.0</priority>
		<data>
			<display>
				<pc_url_pattern>${Options.site_url!}/(\w+).html</pc_url_pattern>
				<html5_url_pattern>${Options.site_mobile_url!}/${r"$"}{1}.html</html5_url_pattern>
			</display>
		</data>
	</url>
	<url>
		<loc>${Options.site_url!}</loc>
		<lastmod>${now?string('yyyy-MM-dd')}</lastmod>
		<changefreq>always</changefreq>
		<priority>1.0</priority>
		<data>
			<display>
				<pc_url_pattern>${Options.site_url!}/(\w+)/(\w+).html</pc_url_pattern>
				<html5_url_pattern>${Options.site_mobile_url!}/${r"$"}{1}/${r"$"}{2}.html</html5_url_pattern>
			</display>
		</data>
	</url>
	<url>
		<loc>${Options.site_url!}</loc>
		<lastmod>${now?string('yyyy-MM-dd')}</lastmod>
		<changefreq>always</changefreq>
		<priority>1.0</priority>
		<data>
			<display>
				<pc_url_pattern>${Options.site_url!}/(\w+)/(\w+)-(\w+)-(\w+)-(\w+)-(\w+).html</pc_url_pattern>
				<html5_url_pattern>${Options.site_mobile_url!}/${r"$"}{1}/${r"$"}{2}-${r"$"}{3}-${r"$"}{4}-${r"$"}{5}-${r"$"}{6}.html</html5_url_pattern>
			</display>
		</data>
	</url>
</urlset>
