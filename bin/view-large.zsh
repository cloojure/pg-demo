#!/bin/zsh

echo ""
echo "running views"
rm -f       view-1.log

echo "Running #1..."
time psql    --dbname=ubuntu_large --log-file=view-1.log --file=1-version-mat-view.sql

echo "Running #2..."
time psql    --dbname=ubuntu_large --log-file=view-1.log --file=2-case-view-drop.sql
time psql    --dbname=ubuntu_large --log-file=view-1.log --file=2-case-view.sql

echo "Running #3..."
time psql    --dbname=ubuntu_large --log-file=view-1.log --file=3-lm-view-drop.sql
time psql    --dbname=ubuntu_large --log-file=view-1.log --file=3-lm-view.sql

echo ""
echo "done"
echo ""

