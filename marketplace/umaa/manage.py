#!/usr/bin/env python
__author__ = "George Alexiou (TEIC)"
__email__ = "g.alexiou@pasiphae.eu"

import os
import sys

if __name__ == "__main__":
    os.environ.setdefault("DJANGO_SETTINGS_MODULE", "umaa.settings")

    from django.core.management import execute_from_command_line

    execute_from_command_line(sys.argv)
