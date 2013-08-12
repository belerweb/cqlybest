<?xml version="1.0" encoding="UTF-8"?>
<urlset>
	<url>
		<loc>${Options.site_url!}</loc>
		<lastmod>${now?string('yyyy-MM-dd')}</lastmod>
		<changefreq>always</changefreq>
		<priority>1.0</priority>
		<data>
			<display>
				<pc_url_pattern>${Options.site_url!}/(.*)</pc_url_pattern>
				<html5_url_pattern>${Options.site_mobile_url!}/${1}</html5_url_pattern>
			</display>
		</data>
	</url>
</urlset>
